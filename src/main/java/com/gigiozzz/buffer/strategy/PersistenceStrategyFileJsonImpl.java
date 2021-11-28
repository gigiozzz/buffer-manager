package com.gigiozzz.buffer.strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gigiozzz.buffer.components.PersistenceStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PersistenceStrategyFileJsonImpl<E extends Serializable> implements PersistenceStrategy<E> {

	private static final Logger logger = LoggerFactory.getLogger(PersistenceStrategyFileJsonImpl.class);

	private Path filePath;
	private Gson gson = new GsonBuilder().create();
	private Class<E> realType;

	public PersistenceStrategyFileJsonImpl(Path filePath, Class<E> clazz) throws IOException {
		this.filePath = filePath;
		this.realType = clazz;

		logger.debug("file path:'{}'", filePath.toAbsolutePath());

		if (!filePath.toFile().exists()) {
			Files.createFile(this.filePath);
			Files.write(filePath, gson.toJson(new ArrayList<E>()).getBytes(StandardCharsets.UTF_8),
					StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		}
	}

	@Override
	public void save(List<E> list) {
		logger.debug("go to save number elements:'{}' of type:'{}' ", list.size(), realType.getClass());
		try {
			Files.write(filePath, gson.toJson(list).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
					StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException ex) {
			logger.error("error save list:'{}'", ex.getMessage(), ex);
		}
	}

	@Override
	public List<E> load() {
		try {
			Type founderListType = TypeToken.getParameterized(ArrayList.class, realType).getType();

			try (BufferedReader br = Files.newBufferedReader(filePath)) {
				List<E> l = gson.fromJson(br, founderListType);
				logger.debug("load number elements:'{}'", l.size());
				return l;
			}
			// return gson.fromJson(new
			// String(Files.readAllBytes(filePath),StandardCharsets.UTF_8),
			// founderListType);
		} catch (IOException ex) {
			logger.error("error load list:'{}'", ex.getMessage(), ex);
		}
		return new ArrayList<>();
	}

//	private void findTypeArguments(Type t) {
//		System.out.println("class: " + t);
//		if (t instanceof ParameterizedType) {
//			Type[] typeArgs = ((ParameterizedType) t).getActualTypeArguments();
//			realType = (Class<E>) typeArgs[0];
//		} else {
//			Class c = (Class) t;
//			findTypeArguments(c.getGenericSuperclass());
//		}
//	}
//	private Class<E> getClassParameter() {
//		Type genericSuperClass = getClass();
//
//		ParameterizedType parametrizedType = null;
//		while (parametrizedType == null) {
//			System.out.println("class: " + genericSuperClass);
//			if ((genericSuperClass instanceof ParameterizedType)) {
//				parametrizedType = (ParameterizedType) genericSuperClass;
//			} else {
//				genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
//			}
//		}
//
//		return (Class<E>) parametrizedType.getActualTypeArguments()[0];
//	}

}

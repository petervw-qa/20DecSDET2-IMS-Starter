package com.qa.ims.controller;

import java.util.List;

public interface ICrudController<T> {

	List<T> readAll();

	T create();

	T update();

	int delete();

}

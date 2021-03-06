package com.roboo.like.google.news.content.utils;

import java.io.IOException;
import java.util.LinkedList;

public abstract class BaseNewsContentUtils
{
	protected static int TIME_OUT=8000;
	protected static final String FOUR_BLANK_SPACE = "        ";
	public abstract LinkedList<String> getNewsContentDataList(String newsUrl) throws IOException;

}

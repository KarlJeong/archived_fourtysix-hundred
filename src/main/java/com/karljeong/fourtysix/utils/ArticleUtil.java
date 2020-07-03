package com.karljeong.fourtysix.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class ArticleUtil {

    public static boolean readArticleFirst(String type, BigInteger articleId) {
    	HttpSession session = UserUtil.getSession();
    	Object readArticle = session.getAttribute("readArticle");
    	if (readArticle == null || "".equals(readArticle)) {
    		Map<String, List<BigInteger>> readArticles = new HashMap<String, List<BigInteger>>();
    		List<BigInteger> articles = new ArrayList<BigInteger>();
    		articles.add(articleId);
    		readArticles.put(type, articles);
    		session.setAttribute("readArticle", readArticles);
    		System.out.println("!!!!! true");
    		return true;
    	}
    	
    	Map<String, List<BigInteger>> readArticles = (Map<String, List<BigInteger>>) readArticle;
    	List<BigInteger> articles = readArticles.get(type);
    	for (BigInteger i : articles) {
    		System.out.println("}}}}} " + i);
    	}
    	System.out.println(">>> " + articleId);
    	if (articles.contains(articleId)) {
    		System.out.println("@@@@@ false");
    		return false;
    	} else {
    		System.out.println("#### true");
    		articles.add(articleId);
    		return true;
    	}
    }

}

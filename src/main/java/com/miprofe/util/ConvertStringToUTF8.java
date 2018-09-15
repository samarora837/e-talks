/**
* Aloprofe. 
 * Copyright © 2015 Aloprofe. 
 * 
 * All rights reserved.
* 
 * THIS PRODUCT CONTAINS CONFIDENTIAL INFORMATION  OF ALOPROFE. 
 * USE, DISCLOSURE OR REPRODUCTION IS PROHIBITED WITHOUT THE 
 * PRIOR EXPRESS WRITTEN PERMISSION OF ALOPROFE.
*/
package com.miprofe.util;

import java.io.UnsupportedEncodingException;

/**
 * @author tgupta1
 *
 */
public class ConvertStringToUTF8 {

	/**
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertStringToUTF8(String text) throws UnsupportedEncodingException{
		
		String UTFText=new String(text.getBytes ("iso-8859-1"), "UTF-8");
		return UTFText;
		
	}

}

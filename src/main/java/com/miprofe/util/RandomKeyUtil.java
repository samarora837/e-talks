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

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author tgupta1
 *
 */
public class RandomKeyUtil {

	private SecureRandom random = new SecureRandom();

    /**
     * @return
     */
    public String nextRandomKey() {
        return new BigInteger(60, random).toString(32);
    }
    
}
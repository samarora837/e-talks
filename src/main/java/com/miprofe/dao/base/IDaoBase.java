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
package com.miprofe.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * The <code>IDaoBase</code> interface contains all
 *            the basic CRUD methods for <code>T</code> entity. Here T is Generic 
 * @author tgupta1
 * 
 */
public interface IDaoBase<T> {
	
	/**
	 * Save the domain
	 * @param domain Domain to save
	 * @author tgupta1
	 */
	public T save(T domain);
	
	/**
	 * Save or Update the domain
	 * @param domain Domain to save
	 * @author tgupta1
	 */
	public T saveOrUpdate(T domain);
	
	/**
	 * update the domain
	 * @param domain Domain to update
	 * @author tgupta1
	 */
	public T update(T domain);
	
	/**
	 * Remove the domain from database
	 * @param domain domain to remove
	 * @author tgupta1
	 */
	public void delete(T domain);

	/**
	 * @param id
	 * @return
	 * @author tgupta1
	 */
	public T get(Serializable id);
	
	/**
	 * Get list by criteria
	 * @param detachedCriteria the domain query criteria, include condition and the orders.
	 * @return
	 * @author tgupta1
	 */
	public List<T> getListByCriteria(DetachedCriteria detachedCriteria);	
	
	
	/**
	 * @param detachedCriteria  Criteria to match the result from table.
	 * @return the unique result matching  criteria.
	 */
	public T getUniqueResultByCriteria(DetachedCriteria detachedCriteria);
	
	public List<T> getAll();
	
}

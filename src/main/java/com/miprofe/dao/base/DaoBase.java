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
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.transaction.annotation.Transactional;

import com.miprofe.entities.ManageCms;
import com.miprofe.entities.TutorChatSessions;


/**
 * The <code>DaoBase</code> class is an implementation of <code>IDaoBase</code>
 * interface for all the basic CRUD methods
 * 
 * @author tgupta1
 * String
 */
public abstract class DaoBase<T> implements IDaoBase<T> {

	
	/**
	 * LOGGER for logging the information inside CRUD methods
	 */
	private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	
	/**
	 * Generic entity class object. 
	 */
	private Class<T> entityClass;
	
	@Resource
	protected SessionFactory sessionFactory;

	/**
	 * @return sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Default Constructor Used to set entityClass object
	 */
	@SuppressWarnings({ "unchecked" })
	public DaoBase() {
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 *  Basic Overrided CRUD methods Implementation
	 */

	@Override
	@Transactional
	public void delete(T domain) {
		LOGGER.info("Deleting Object...Inside delete.......");
		sessionFactory.getCurrentSession().delete(domain);
	}

	@Override
	@Transactional
	public T save(T domain) {
		
		sessionFactory.getCurrentSession().save(domain);
		return domain;
	}

	@Override
	@Transactional
	public T update(T domain) {
		
		sessionFactory.getCurrentSession().update(domain);
		return domain;
	}

	@Override
	@Transactional
	public T saveOrUpdate(T domain) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public T get(Serializable id) {
		
		return (T) sessionFactory.getCurrentSession().get(entityClass, id);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<T> getListByCriteria(DetachedCriteria criteria) {
		
		return criteria.getExecutableCriteria(
				sessionFactory.getCurrentSession()).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public T getUniqueResultByCriteria(DetachedCriteria detachedCriteria) {

		return (T) DataAccessUtils.uniqueResult(detachedCriteria
				.getExecutableCriteria(sessionFactory.getCurrentSession())
				.list());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
    public List<T> getAll() {
		
        return sessionFactory.getCurrentSession().createQuery("from ".concat(entityClass.getName())).list();
    }

	public List<TutorChatSessions> getAllChatRequestDetailsByTutorProfileId(
			int tutor_Profile_Id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ManageCms getPageContentByPageName(String pageName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 
}
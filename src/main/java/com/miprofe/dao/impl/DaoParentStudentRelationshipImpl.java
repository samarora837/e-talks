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

package com.miprofe.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.miprofe.dao.DaoParentStudentRelationship;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.ParentStudentRelationship;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoParentStudentRelationshipImpl extends DaoBase<ParentStudentRelationship> implements DaoParentStudentRelationship {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getRelationDetailsByStudentEmail(java.lang.String)
	 */
	@Override
	@Transactional
	public ParentStudentRelationship getRelationDetailsByStudentEmail(String email) {
		 ParentStudentRelationship parentStudentRelationship = (ParentStudentRelationship) sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where studentEmail='"+email+"' AND is_Verified='Y'").uniqueResult();
		 return parentStudentRelationship!=null?parentStudentRelationship:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getRelationListByStudentEmail(java.lang.String)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ParentStudentRelationship> getRelationListByStudentEmail(
			String email) {
		 List<ParentStudentRelationship> parentStudentRelationshipList = sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where studentEmail='"+email+"'").list();
		 return parentStudentRelationshipList!=null?parentStudentRelationshipList:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getRelationRecordByParentStudentEmail(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ParentStudentRelationship getRelationRecordByParentStudentEmail(String parentEmail, String studentEmail) {
		List<ParentStudentRelationship> parentStudentRelationship =  sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where studentEmail='"+studentEmail+"' AND parentEmail='"+parentEmail+"'").list();
		 return (parentStudentRelationship!=null && parentStudentRelationship.size()>0)?parentStudentRelationship.get(0):null; 
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getRelationRecordByParentEmail(java.lang.String)
	 */
	@Override
	@Transactional
	public ParentStudentRelationship getRelationRecordByParentEmail(String parentEmail) {
		ParentStudentRelationship parentStudentRelationship = (ParentStudentRelationship) sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where parentEmail='"+parentEmail+"'").uniqueResult();
		 return parentStudentRelationship!=null?parentStudentRelationship:null; 
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getRelationListByParentEmail(java.lang.String)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ParentStudentRelationship> getRelationListByParentEmail(String parentEmail) {
		List<ParentStudentRelationship> parentStudentRelationshipList = sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where parentEmail='"+parentEmail+"'").list();
		 return parentStudentRelationshipList!=null?parentStudentRelationshipList:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getStudentRecordByStudentEmail(java.lang.String)
	 */
	@Override
	@Transactional
	public ParentStudentRelationship getStudentRecordByStudentEmail(String studentEmail) {
		ParentStudentRelationship parentStudentRelationship = (ParentStudentRelationship) sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where studentEmail='"+studentEmail+"'").uniqueResult();
		 return parentStudentRelationship!=null?parentStudentRelationship:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getListByParentProfileId(int)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ParentStudentRelationship> getListByParentProfileId(int parentProfileId) {
		List<ParentStudentRelationship> parentStudentRelationshipList = sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where parentProfileDetail.parent_Id="+parentProfileId).list();
		 return parentStudentRelationshipList!=null?parentStudentRelationshipList:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getParentDetailsAddedByStudent(int)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ParentStudentRelationship> getParentDetailsAddedByStudent(
			int student_Id) {
		List<ParentStudentRelationship> parentStudentRelationshipList = sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where addedBy='student' and studentProfileDetail.student_Profile_Id="+student_Id).list();
		 return parentStudentRelationshipList!=null?parentStudentRelationshipList:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getByParentEmailAndStudentEmail(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public ParentStudentRelationship getByParentEmailAndStudentEmail(
			String parentEmail, String studentEmail) {
		ParentStudentRelationship parentStudentRelationship = (ParentStudentRelationship) sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where studentEmail='"+studentEmail+"' and parentEmail='"+parentEmail+"'").uniqueResult();
		 return parentStudentRelationship!=null?parentStudentRelationship:null;
	}
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getParentDetailsAddedByParent(int)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ParentStudentRelationship> getParentDetailsAddedByParent(
			int student_Id) {
		List<ParentStudentRelationship> parentStudentRelationshipList = sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where addedBy='parent' and studentProfileDetail.student_Profile_Id="+student_Id).list();
		 return parentStudentRelationshipList!=null?parentStudentRelationshipList:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getStudentDetailsAddedByParent(int)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ParentStudentRelationship> getStudentDetailsAddedByParent(
			int parent_Id) {
		List<ParentStudentRelationship> parentStudentRelationshipList = sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where addedBy='parent' and parentProfileDetail.parent_Id="+parent_Id).list();
		 return parentStudentRelationshipList!=null?parentStudentRelationshipList:null;
	}
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getStudentDetailsAddedByStudent(int)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ParentStudentRelationship> getStudentDetailsAddedByStudent(
			int parent_Id) {
		List<ParentStudentRelationship> parentStudentRelationshipList = sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where addedBy='student' and parentProfileDetail.parent_Id="+parent_Id).list();
		 return parentStudentRelationshipList!=null?parentStudentRelationshipList:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getParentProfileIdByStudentProfileId(int)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ParentStudentRelationship getParentProfileIdByStudentProfileId(int student_Profile_Id) {
		List<ParentStudentRelationship> parentStudentRelationshipList =  sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where studentProfileDetail.student_Profile_Id='"+student_Profile_Id+"' AND is_Verified='Y'").list();
		return parentStudentRelationshipList.size()>0?parentStudentRelationshipList.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getStudentProfileIdByParentProfileId(int)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ParentStudentRelationship getStudentProfileIdByParentProfileId(
			int parent_Id) {
		List<ParentStudentRelationship> parentStudentRelationshipList =  sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where parentProfileDetail.parent_Id='"+parent_Id+"' AND is_Verified='Y'").list();
		return parentStudentRelationshipList.size()>0?parentStudentRelationshipList.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getStudentDetailRegWithParent(int)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ParentStudentRelationship> getStudentDetailRegWithParent(
			int parentProfileId) {
		List<ParentStudentRelationship> parentStudentRelationshipList = sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where is_Verified='Y' and parentProfileDetail.parent_Id="+parentProfileId).list();
		 return parentStudentRelationshipList!=null?parentStudentRelationshipList:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getRelationListByParentEmailVerified(java.lang.String)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ParentStudentRelationship> getRelationListByParentEmailVerified(
			String parentEmail) {
		List<ParentStudentRelationship> parentStudentRelationshipList = sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where is_Verified='Y' and parentEmail='"+parentEmail+"'").list();
		 return parentStudentRelationshipList!=null?parentStudentRelationshipList:null;
	}
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getRelationListByStudentEmailVerified(java.lang.String)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ParentStudentRelationship> getRelationListByStudentEmailVerified(
			String studentEmail) {
		List<ParentStudentRelationship> parentStudentRelationshipList = sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where is_Verified='Y' and studentEmail='"+studentEmail+"'").list();
		 return parentStudentRelationshipList!=null?parentStudentRelationshipList:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentStudentRelationship#getRelationshipDetailsByStudentProfileId(int)
	 */
	@Override
	@Transactional
	public List<ParentStudentRelationship> getRelationshipDetailsByStudentProfileId(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<ParentStudentRelationship> parentStudentRelationshipList = sessionFactory.getCurrentSession().createQuery("from ParentStudentRelationship where is_Verified='Y' and studentProfileDetail.student_Profile_Id='"+student_Profile_Id+"'").list();
		 return parentStudentRelationshipList!=null?parentStudentRelationshipList:null;
	}
	
	
}

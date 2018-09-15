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

import com.miprofe.dao.DaoReviewRelation;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.ReviewRelation;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoReviewRelationImpl extends DaoBase<ReviewRelation> implements DaoReviewRelation {

	@Override
	@Transactional
	public List<ReviewRelation> getReviewRelationByStudentProfileId(int studentProfileId) {
		@SuppressWarnings("unchecked")
		List<ReviewRelation> list=sessionFactory.getCurrentSession().createQuery("from ReviewRelation where studentProfileDetail.student_Profile_Id="+studentProfileId).list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<ReviewRelation> getReviewRelationDetailsByTutorProfileId(int tutorProfileId) {
		@SuppressWarnings("unchecked")
		List<ReviewRelation> list=sessionFactory.getCurrentSession().createQuery("from ReviewRelation where tutorProfileDetail.tutor_Profile_Id="+tutorProfileId+" AND reviewSession.is_deleted='N' AND reviewSession.is_completed='N'").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public ReviewRelation getReviewDetailByBookingId(int bookingId) {
		@SuppressWarnings("unchecked")
		ReviewRelation reviewRelation =(ReviewRelation) sessionFactory.getCurrentSession().createQuery("from ReviewRelation where reviewSession.booking_id="+bookingId).uniqueResult();
		return reviewRelation;
	}

	@Override
	@Transactional
	public List<ReviewRelation> getAllReviewRelationDetailsByTutorProfileId(int tutor_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<ReviewRelation> list=sessionFactory.getCurrentSession().createQuery("from ReviewRelation where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id).list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<ReviewRelation> getAllUnreadReviewRelationByTutorProfileId(int tutor_Profile_Id) {
	@SuppressWarnings("unchecked")
	List<ReviewRelation> list = sessionFactory.getCurrentSession().createQuery("from ReviewRelation where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id+" AND read_status_tutor='N'").list();
	return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public void updateReviewSessionreadStatusByTutorProfileId(int tutor_Profile_Id) {
		sessionFactory.getCurrentSession().createQuery("update ReviewRelation set read_status_tutor = 'Y'  where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id).executeUpdate();
		
		
	}

	@Override
	@Transactional
	public List<ReviewRelation> getAllUnreadReviewRelationByStudentProfileId(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<ReviewRelation> list = sessionFactory.getCurrentSession().createQuery("from ReviewRelation where studentProfileDetail.student_Profile_Id="+student_Profile_Id+" AND read_status_student='N'").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public void updateReviewSessionreadStatusByStudentProfileId(int student_Profile_Id) {
		sessionFactory.getCurrentSession().createQuery("update ReviewRelation set read_status_student = 'Y'  where studentProfileDetail.student_Profile_Id="+student_Profile_Id).executeUpdate();
		
		
	}

	@Override
	@Transactional
	public List<ReviewRelation> getAllUnreadProposedReviewRelationByStudentProfileId(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<ReviewRelation> list = sessionFactory.getCurrentSession().createQuery("from ReviewRelation where studentProfileDetail.student_Profile_Id="+student_Profile_Id+" AND proposed_readByStudent='N' AND reviewSession.is_proposed_byTutor='Y'").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public void updateAllProposedSessionToRead(int student_Profile_Id) {
	//	sessionFactory.getCurrentSession().createQuery("update ReviewRelation set proposed_readByStudent='Y' where (select from ReviewSession where is_proposed_byTutor='Y') AND studentProfileDetail.student_Profile_Id="+student_Profile_Id).executeUpdate();
		sessionFactory.getCurrentSession().createQuery("update ReviewRelation set proposed_readByStudent='Y' where  studentProfileDetail.student_Profile_Id="+student_Profile_Id).executeUpdate();
	}

	@Override
	@Transactional
	public List<ReviewRelation> getAllUnreadApprovedReviewRelationByTutorProfileId(int tutor_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<ReviewRelation> list = sessionFactory.getCurrentSession().createQuery("from ReviewRelation where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id+" AND accepted_readByTutor='N'  AND reviewSession.accepted='Y'").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public void updateAssignedReviewSessionreadStatusByTutorProfileId(int tutor_Profile_Id) {
	//	sessionFactory.getCurrentSession().createQuery("update ReviewRelation set accepted_readByTutor = 'Y'  where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id+"  AND reviewSession.accepted='Y'").executeUpdate();
		sessionFactory.getCurrentSession().createQuery("update ReviewRelation set accepted_readByTutor = 'Y'  where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id).executeUpdate();
	}

	@Override
	@Transactional
	public List<ReviewRelation> getAllUnreadCompletedReviewRelationByStudentProfileId(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<ReviewRelation> list = sessionFactory.getCurrentSession().createQuery("from ReviewRelation where studentProfileDetail.student_Profile_Id="+student_Profile_Id+" AND work_readByStudent='N' AND reviewSession.is_completed_by_tutor='Y'").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public void updateAllCompletedSessionToRead(int student_Profile_Id) {
	//	sessionFactory.getCurrentSession().createQuery("update ReviewRelation set work_readByStudent = 'Y'  where studentProfileDetail.student_Profile_Id="+student_Profile_Id+" AND reviewSession.is_completed_by_tutor='Y'").executeUpdate();
		sessionFactory.getCurrentSession().createQuery("update ReviewRelation set work_readByStudent = 'Y'  where studentProfileDetail.student_Profile_Id="+student_Profile_Id).executeUpdate();
	}


	
}

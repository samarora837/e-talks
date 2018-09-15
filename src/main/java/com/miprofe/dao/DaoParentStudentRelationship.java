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

package com.miprofe.dao;

import java.util.List;

import com.miprofe.dao.base.IDaoBase;
import com.miprofe.entities.ParentStudentRelationship;

/**
 * @author tgupta1
 *
 */
public interface DaoParentStudentRelationship extends IDaoBase<ParentStudentRelationship>{

	ParentStudentRelationship getRelationDetailsByStudentEmail(String email);
	
	List<ParentStudentRelationship> getRelationListByStudentEmail(String email);

	ParentStudentRelationship getRelationRecordByParentStudentEmail(String username, String string);

	ParentStudentRelationship getRelationRecordByParentEmail(String username);

	List<ParentStudentRelationship> getRelationListByParentEmail(String username);

	ParentStudentRelationship getStudentRecordByStudentEmail(String string);


	List<ParentStudentRelationship> getListByParentProfileId(int parent_Id);
	
	List<ParentStudentRelationship> getParentDetailsAddedByStudent(int student_Id);
	
	ParentStudentRelationship getByParentEmailAndStudentEmail(String parentEmail, String studentEmail);
	
	List<ParentStudentRelationship> getParentDetailsAddedByParent(int student_Id);
	
	List<ParentStudentRelationship> getStudentDetailsAddedByParent(int parent_Id);
	
	List<ParentStudentRelationship> getStudentDetailsAddedByStudent(int parent_Id);

	ParentStudentRelationship getParentProfileIdByStudentProfileId(
			int student_Profile_Id);

	ParentStudentRelationship getStudentProfileIdByParentProfileId(int parent_Id);
	
	
	List<ParentStudentRelationship> getStudentDetailRegWithParent(int parentProfileId);
	
	List<ParentStudentRelationship> getRelationListByParentEmailVerified(String parentEmail);
	List<ParentStudentRelationship> getRelationListByStudentEmailVerified(String studentEmail);

	List<ParentStudentRelationship> getRelationshipDetailsByStudentProfileId(
			int student_Profile_Id);
	
	
}

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<%          response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
            response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
            response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
 %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MiProfe | <spring:message code="manage.plans"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta content="" name="description"/>
<meta content="" name="author"/>
<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>
<body class="page-header-fixed page-quick-sidebar-over-content " onload="navigationUpdate();">
<!-- BEGIN HEADER -->
<div class="page-header -i navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<%@ include file="/WEB-INF/views/views-admin/sadmin/header.jsp" %>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<%@ include file="/WEB-INF/views/views-admin/sadmin/nav-sidebar.jsp" %>
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			<spring:message code="plans"/> <small><spring:message code="manage.allplans"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/sys-admin/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/sys-admin/manage-plan"><spring:message code="student.plan"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/sys-admin/manage-plan"><spring:message code="manage.plans"/></a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="manage.allplans"/>
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div> -->
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="btn-group">
											<button class="btn green" onclick="addNewSubject();">
											<spring:message code="add.new"/> <i class="fa fa-plus"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
							<!--BEGIN TABS-->
							<div class="tabbable-line tabbable-full-width">
								<ul class="nav nav-tabs">
									<li class="active">
										<a href="#tab_1_1" data-toggle="tab">
										<spring:message code="student.basic"/> </a>
									</li>
									<li>
										<a href="#tab_1_2" data-toggle="tab">
										<spring:message code="student.popular"/> </a>
									</li>
									<li>
										<a href="#tab_1_3" data-toggle="tab">
										<spring:message code="student.plus"/> </a>
									</li>
									<li>
										<a href="#tab_1_4" data-toggle="tab">
										<spring:message code="topup"/> </a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane active" id="tab_1_1">
										<div class="row">
											<div class="col-md-12">
												<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
													<thead>
														<tr>
															<th>
																<spring:message code="country.name"/> 
															</th>
															<th>
																<spring:message code="plan.amount"/> 
															</th>
															<th>
																<spring:message code="hours"/> 
															</th>
															<th>
																<spring:message code="action"/> 
															</th>
														</tr>
													</thead>
													<tbody>
														<c:set var="count" value="0" scope="page" />
														<c:set var="defaultBasicRate" value="0" scope="page" />
														<c:set var="defaultBasicPlanHour" value="0" scope="page" />
														<c:set var="defaultBasicPlanId" value="0" scope="page" />
														
														
															<c:forEach var="basicPlanRatesList" items="${basicPlanRates}">
															
															<c:if test="${basicPlanRatesList.countryMaster eq null}">
															<c:set var="defaultBasicRate" value="${basicPlanRatesList.rate}" scope="page"/>
															<c:set var="defaultBasicPlanHour" value="${basicPlanRatesList.hours}" scope="page"/>
															<c:set var="defaultBasicPlanId" value="${basicPlanRatesList.plan_Rate_Id}" scope="page"/>
															</c:if>
															
																<tr>
																	<td>
																		 ${basicPlanRatesList.countryMaster.country_Name}
																	</td>
																	<td>
																		${basicPlanRatesList.rate}/<spring:message code="hour"/>
																	</td>
																	<td>
																		${basicPlanRatesList.hours}/<spring:message code="month"/>
																	</td>
																	<td>
																		<a onclick="editPlan('${basicPlanRatesList.plan_Rate_Id}');">
																		<spring:message code="edit"/> </a>
																		<a class="delete" onclick="setDelete('${basicPlanRatesList.plan_Rate_Id}');">
																		<spring:message code="delete"/> </a>
																	</td>
																</tr>
																	<c:set var="count" value="${count + 1}" scope="page"/>
															</c:forEach>
															 <tr>
																	<td>
																		<spring:message code="otherCountries"/>
																	</td>
																	<td>
																		${defaultBasicRate}/<spring:message code="hour"/>
																	</td>
																	<td>
																		${defaultBasicPlanHour}/<spring:message code="month"/>
																	</td>
																	<td>
																		<a onclick="editPlan('${defaultBasicPlanId}');">
																		<spring:message code="edit"/> </a>
																		
																	</td>
																</tr> 
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<!--tab_1_2-->
									<div class="tab-pane" id="tab_1_2">
										<div class="row">
											<div class="col-md-12">
												<table class="table table-striped table-hover table-bordered" id="sample_editable_2">
													<thead>
														<tr>
															<th>
																<spring:message code="country.name"/> 
															</th>
															<th>
																<spring:message code="plan.amount"/> 
															</th>
															<th>
																<spring:message code="hours"/> 
															</th>
															<th>
																<spring:message code="action"/> 
															</th>
														</tr>
													</thead>
													<tbody>
														<c:set var="count" value="0" scope="page" />
														
														<c:set var="defaultPopularRate" value="0" scope="page" />
														<c:set var="defaultPopularPlanHour" value="0" scope="page" />
														<c:set var="defaultPopularPlanId" value="0" scope="page" />
														
															<c:forEach var="popularPlanRatesList" items="${popularPlanRates}">
															
															<c:if test="${basicPlanRatesList.countryMaster eq null}">
															<c:set var="defaultPopularRate" value="${popularPlanRatesList.rate}" scope="page"/>
															<c:set var="defaultPopularPlanHour" value="${popularPlanRatesList.hours}" scope="page"/>
															<c:set var="defaultPopularPlanId" value="${popularPlanRatesList.plan_Rate_Id}" scope="page"/>
															</c:if>
															
																<tr>
																	<td>
																		 ${popularPlanRatesList.countryMaster.country_Name}
																	</td>
																	<td>
																		${popularPlanRatesList.rate}/<spring:message code="hour"/>
																	</td>
																	<td>
																		${popularPlanRatesList.hours}/<spring:message code="month"/>
																	</td>
																	<td>
																		<a onclick="editPlan('${popularPlanRatesList.plan_Rate_Id}');">
																		<spring:message code="edit"/> </a>
																		<a class="delete" onclick="setDelete('${popularPlanRatesList.plan_Rate_Id}');">
																		<spring:message code="delete"/> </a>
																	</td>
																</tr>
																	<c:set var="count" value="${count + 1}" scope="page"/>
															</c:forEach>
															
															 <tr>
																	<td>
																		<spring:message code="otherCountries"/>
																	</td>
																	<td>
																		${defaultPopularRate}/<spring:message code="hour"/>
																	</td>
																	<td>
																		${defaultPopularPlanHour}/<spring:message code="month"/>
																	</td>
																	<td>
																		<a onclick="editPlan('${defaultPopularPlanId}');">
																		<spring:message code="edit"/> </a>
																		
																	</td>
																</tr>
															
															
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="tab-pane" id="tab_1_3">
										<div class="row">
											<div class="col-md-12">
												<table class="table table-striped table-hover table-bordered" id="sample_editable_3">
													<thead>
														<tr>
															<th>
																<spring:message code="country.name"/> 
															</th>
															<th>
																<spring:message code="plan.amount"/> 
															</th>
															<th>
																<spring:message code="hours"/> 
															</th>
															<th>
																<spring:message code="action"/> 
															</th>
														</tr>
													</thead>
													<tbody>
														<c:set var="count" value="0" scope="page" />
														<c:set var="defaultPlusRate" value="0" scope="page" />
														<c:set var="defaultPlusPlanHour" value="0" scope="page" />
														<c:set var="defaultPlusPlanId" value="0" scope="page" />
														
															<c:forEach var="PlusPlanRatesList" items="${PlusPlanRates}">
															
																<c:if test="${basicPlanRatesList.countryMaster eq null}">
															<c:set var="defaultPlusRate" value="${PlusPlanRatesList.rate}" scope="page"/>
															<c:set var="defaultPlusPlanHour" value="${PlusPlanRatesList.hours}" scope="page"/>
															<c:set var="defaultPlusPlanId" value="${PlusPlanRatesList.plan_Rate_Id}" scope="page"/>
															</c:if>
																<tr>
																	<td>
																		 ${PlusPlanRatesList.countryMaster.country_Name}
																	</td>
																	<td>
																		${PlusPlanRatesList.rate}/<spring:message code="hour"/>
																	</td>
																	<td>
																		${PlusPlanRatesList.hours}/<spring:message code="month"/>
																	</td>
																	<td>
																		<a onclick="editPlan('${PlusPlanRatesList.plan_Rate_Id}');">
																		<spring:message code="edit"/> </a>
																		<a class="delete" onclick="setDelete('${PlusPlanRatesList.plan_Rate_Id}');">
																		<spring:message code="delete"/> </a>
																	</td>
																</tr>
																	<c:set var="count" value="${count + 1}" scope="page"/>
															</c:forEach>
															
															 <tr>
																	<td>
																		<spring:message code="otherCountries"/>
																	</td>
																	<td>
																		${defaultPlusRate}/<spring:message code="hour"/>
																	</td>
																	<td>
																		${defaultPlusPlanHour}/<spring:message code="month"/>
																	</td>
																	<td>
																		<a onclick="editPlan('${defaultPlusPlanId}');">
																		<spring:message code="edit"/> </a>
																		
																	</td>
																</tr>
															
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="tab-pane" id="tab_1_4">
										<div class="row">
											<div class="col-md-12">
												<table class="table table-striped table-hover table-bordered" id="sample_editable_4">
													<thead>
														<tr>
															<th>
																<spring:message code="country.name"/> 
															</th>
															<th>
																<spring:message code="plan.amount"/> 
															</th>
															<th>
																<spring:message code="hours"/> 
															</th>
															<th>
																<spring:message code="action"/> 
															</th>
														</tr>
													</thead>
													<tbody>
														<c:set var="count" value="0" scope="page" />
														
														<c:set var="defaultTopRate" value="0" scope="page" />
														<c:set var="defaultTopPlanHour" value="0" scope="page" />
														<c:set var="defaultTopPlanId" value="0" scope="page" />
														
															<c:forEach var="TopUpRatesList" items="${TopUpRates}">
															
															<c:if test="${basicPlanRatesList.countryMaster eq null}">
															<c:set var="defaultTopRate" value="${TopUpRatesList.rate}" scope="page"/>
															<c:set var="defaultTopPlanHour" value="${TopUpRatesList.hours}" scope="page"/>
															<c:set var="defaultTopPlanId" value="${TopUpRatesList.plan_Rate_Id}" scope="page"/>
															</c:if>
																<tr>
																	<td>
																		 ${TopUpRatesList.countryMaster.country_Name}
																	</td>
																	<td>
																		${TopUpRatesList.rate}/<spring:message code="hour"/> 
																	</td>
																	<td>
																		${TopUpRatesList.hours}/<spring:message code="month"/> 
																	</td>
																	<td>
																		<a onclick="editPlan('${TopUpRatesList.plan_Rate_Id}');">
																		<spring:message code="edit"/>  </a>
																		<a class="delete" onclick="setDelete('${TopUpRatesList.plan_Rate_Id}');">
																		<spring:message code="delete"/>  </a>
																	</td>
																</tr>
																	<c:set var="count" value="${count + 1}" scope="page"/>
															</c:forEach>
															
															
															 <tr>
																	<td>
																		<spring:message code="otherCountries"/>
																	</td>
																	<td>
																		${defaultTopRate}/<spring:message code="hour"/>
																	</td>
																	<td>
																		${defaultTopPlanHour}/<spring:message code="month"/>
																	</td>
																	<td>
																		<a onclick="editPlan('${defaultTopPlanId}');">
																		<spring:message code="edit"/> </a>
																		
																	</td>
																</tr>
															
													</tbody>
												</table>
											</div>
										</div>
									</div>
								<!--end tab-pane-->
								</div>
							</div>
						<!--END TABS-->
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT -->
</div>

					<!-- New Dialog start -->
						<div id="addNewPlan" class="modal fade" tabindex="-1" data-width="460">
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close clearFormFields" data-dismiss="modal" aria-hidden="true"></button>
									<h3 class="modal-title"><spring:message code="add.plans"/> </h3>
								</div>
								<div class="modal-body">
									<!-- <div class="row"> -->
										<div class="row profile">
											<div class="col-md-12">
												<!--BEGIN TABS-->
												<div class="tabbable-line tabbable-full-width">
													<ul class="nav nav-tabs">
														<li class="active">
															<a href="#tabBasic" data-toggle="tab">
															<spring:message code="student.basic"/> </a>
														</li>
														<li>
															<a href="#tabPopular" data-toggle="tab">
															<spring:message code="student.popular"/> </a>
														</li>
														<li>
															<a href="#tabPlus" data-toggle="tab">
															<spring:message code="student.plus"/> </a>
														</li>
														<li>
															<a href="#tabTopup" data-toggle="tab">
															<spring:message code="topup"/> </a>
														</li>
													</ul>
													<div class="tab-content">
														<div class="tab-pane active" id="tabBasic">
															<div class="row">
																<div class="col-md-12">
																	<form:form name="updateAdmin" id="form_sample_1" commandName="dtoManagePlans" method="post" action="savePlan">
																	<div class="form-body">
																		<div class="alert alert-danger display-hide">
																				<button class="close" data-close="alert"></button>
																				<spring:message code="havesome.error"></spring:message>
																			</div>
																			<div class="alert alert-success display-hide">
																				<button class="close" data-close="alert"></button>
																				<spring:message code="form.validation"></spring:message>
																			</div>
																			<div class="modal-body">
																				<div class="row">
																					<div class="col-md-12">
																					<div class="form-group">
																						<h4><spring:message code="country"/></h4>
																						<p>
																						<form:select path="countryId" class="bs-select form-control" id="countryId" name="countryName">
																							<optgroup label='<spring:message code="spanishspeakingcountries"/>' id="spanishCountries">
																							<c:forEach var="listSpanishCountryBasic" items="${listSpanishCountryBasic}">
											                    										<option value='${listSpanishCountryBasic.key}'>${listSpanishCountryBasic.value}</option>
											                    							</c:forEach>
																							</optgroup>
																							<optgroup label='<spring:message code="otherCountries"/>' id="otherCountries">
																							<c:forEach var="listOtherCountryBasic" items="${listOtherCountryBasic}">
											                    										<option value='${listOtherCountryBasic.key}' >${listOtherCountryBasic.value}</option>
											                    							</c:forEach>
																							</optgroup>
											</form:select>
											</p>
										<h4><spring:message code="hours/month"/></h4>
											<p>
												<form:input path="hours" id="hours" maxlength="4" name="hours" class="form-control" type="text"></form:input>
											</p>
										<h4><spring:message code="Rate.Hour"/> </h4>
											<p>
												<form:input path="rate" id="rate" maxlength="4"  name="rate" class="form-control" type="text"></form:input>
											</p>
										<h4><spring:message code="description"/></h4>
											<p>
												<form:textarea style="resize:none;" path="description" class="form-control" id="description" name="description" rows="3" placeholder=""></form:textarea>
											</p>
										</div>
										</div>
									</div>
								</div>
							<form:input type="hidden" path="planId" value="1" id="planId" name="planId"></form:input>
							<div class="modal-footer">
								<button type="button" data-dismiss="modal" class="btn btn-default clearFormFields"><spring:message code="admin.close"/></button>
								<button type="submit" class="btn blue"><spring:message code="save"/></button>
							</div>
							
							
							</div>
						</form:form>																	
																</div>
															</div>
														</div>
														<!--tab_1_2-->
														<div class="tab-pane" id="tabPopular">
															<div class="row">
																<div class="col-md-12">
																	<form:form name="updateAdmin" id="form_sample_pass" commandName="dtoManagePlans" method="post" action="savePlan">
																	<div class="form-body">
																		<div class="alert alert-danger display-hide">
																				<button class="close clearFormFields" data-close="alert"></button>
																				<spring:message code="havesome.error"></spring:message>
																			</div>
																			<div class="alert alert-success display-hide">
																				<button class="close" data-close="alert"></button>
																				<spring:message code="form.validation"></spring:message>
																			</div>
																			<div class="modal-body">
																				<div class="row">
																					<div class="col-md-12">
																					<div class="form-group">
																						<h4><spring:message code="country"/></h4>
																						<p>
																						<form:select path="countryId" class="bs-select form-control" id="countryId" name="countryName">
																							<optgroup label='<spring:message code="spanishspeakingcountries"/>' id="spanishCountries">
																							<c:forEach var="listSpanishCountryPopular" items="${listSpanishCountryPopular}">
											                    										<option value='${listSpanishCountryPopular.key}'>${listSpanishCountryPopular.value}</option>
											                    							</c:forEach>
																							</optgroup>
																							<optgroup label='<spring:message code="otherCountries"/>' id="otherCountries">
																							<c:forEach var="listOtherCountryPopular" items="${listOtherCountryPopular}">
											                    										<option value='${listOtherCountryPopular.key}' >${listOtherCountryPopular.value}</option>
											                    							</c:forEach>
																							</optgroup>
																						</form:select>
																						</p>
																					<h4><spring:message code="hours/month"/></h4>
																						<p>
																							<form:input path="hours" id="hours" maxlength="4"  name="hours" class="form-control" type="text"></form:input>
																						</p>
																					<h4><spring:message code="Rate.Hour"/> </h4>
																						<p>
																							<form:input path="rate" id="rate" maxlength="4"  name="rate" class="form-control" type="text"></form:input>
																						</p>
																				<h4><spring:message code="description"/></h4>
																						<p>
																							<form:textarea  style="resize:none;" path="description" class="form-control" id="description" name="description" rows="3" placeholder=""></form:textarea>
																						</p>
																					</div>
																					</div>
																				</div>
																			</div>
																		<form:input type="hidden" path="planId" value="2" id="planId" name="planId"></form:input>
																		<div class="modal-footer">
																			<button type="button" data-dismiss="modal" class="btn btn-default clearFormFields"><spring:message code="admin.close"/></button>
																			<button type="submit" class="btn blue"><spring:message code="save"/></button>
																		</div>
																		</div>
																	</form:form>																
																</div>
															</div>
														</div>
														<div class="tab-pane" id="tabPlus">
															<div class="row">
																<div class="col-md-12">
																	<form:form name="updateAdmin" id="form_sample_11" commandName="dtoManagePlans" method="post" action="savePlan">
																	<div class="form-body">
																		<div class="alert alert-danger display-hide">
																				<button class="close clearFormFields" data-close="alert"></button>
																				<spring:message code="form.validation"></spring:message>
																			</div>
																			<div class="alert alert-success display-hide">
																				<button class="close" data-close="alert"></button>
																				<spring:message code="form.validation"></spring:message>
																			</div>
																			<div class="modal-body">
																				<div class="row">
																					<div class="col-md-12">
																					<div class="form-group">
																						<h4><spring:message code="country"/></h4>
																						<p>
																						<form:select path="countryId" class="bs-select form-control" id="countryId" name="countryName">
																							<optgroup label='<spring:message code="spanishspeakingcountries"/>' id="spanishCountries">
																							<c:forEach var="listSpanishCountryPlus" items="${listSpanishCountryPlus}">
											                    										<option value='${listSpanishCountryPlus.key}'>${listSpanishCountryPlus.value}</option>
											                    							</c:forEach>
																							</optgroup>
																							<optgroup label='<spring:message code="otherCountries"/>' id="otherCountries">
																							<c:forEach var="listOtherCountryPlus" items="${listOtherCountryPlus}">
											                    										<option value='${listOtherCountryPlus.key}' >${listOtherCountryPlus.value}</option>
											                    							</c:forEach>
																							</optgroup>
																						</form:select>
																						</p>
																					<h4><spring:message code="hours/month"/></h4>
																						<p>
																							<form:input path="hours" id="hours" maxlength="4"  name="hours" class="form-control" type="text"></form:input>
																						</p>
																					<h4><spring:message code="Rate.Hour"/> </h4>
																						<p>
																							<form:input path="rate" id="rate" maxlength="4"  name="rate" class="form-control" type="text"></form:input>
																						</p>
																					<h4><spring:message code="description"/></h4>
																						<p>
																							<form:textarea  style="resize:none;"  path="description" class="form-control" id="description" name="description" rows="3" placeholder=""></form:textarea>
																						</p>
																					</div>
																					</div>
																				</div>
																			</div>
																		<form:input type="hidden" path="planId" value="3" id="planId" name="planId"></form:input>
																		<div class="modal-footer">
																			<button type="button" data-dismiss="modal" class="btn btn-default clearFormFields"><spring:message code="admin.close"/></button>
																			<button type="submit" class="btn blue"><spring:message code="save"/></button>
																		</div>
																		</div>
																	</form:form>																
																</div>
															</div>
														</div>
														<div class="tab-pane" id="tabTopup">
															<div class="row">
																<div class="col-md-12">
																	<form:form name="updateAdmin" id="form_sample_111" commandName="dtoManagePlans" method="post" action="savePlan">
																	<div class="form-body">
																		<div class="alert alert-danger display-hide">
																				<button class="close clearFormFields" data-close="alert"></button>
																				<spring:message code="form.validation"></spring:message>
																			</div>
																			<div class="alert alert-success display-hide">
																				<button class="close" data-close="alert"></button>
																				<spring:message code="form.validation"></spring:message>
																			</div>
																			<div class="modal-body">
																				<div class="row">
																					<div class="col-md-12">
																					<div class="form-group">
																						<h4><spring:message code="country"/></h4>
																						<p>
																						<form:select path="countryId" class="bs-select form-control" id="countryId" name="countryName">
																							<optgroup label='<spring:message code="spanishspeakingcountries"/>' id="spanishCountries">
																							<c:forEach var="listSpanishCountryTopUp" items="${listSpanishCountryTopUp}">
											                    										<option value='${listSpanishCountryTopUp.key}'>${listSpanishCountryTopUp.value}</option>
											                    							</c:forEach>
																							</optgroup>
																							<optgroup label='<spring:message code="otherCountries"/>' id="otherCountries">
																							<c:forEach var="listOtherCountryTopUp" items="${listOtherCountryTopUp}">
											                    										<option value='${listOtherCountryTopUp.key}' >${listOtherCountryTopUp.value}</option>
											                    							</c:forEach>
																							</optgroup>
																						</form:select>
																						</p>
																						<h4><spring:message code="hours/month"/></h4>
																						<p>
																							<form:input path="hours" id="hours" maxlength="4"  name="hours" class="form-control" type="text"></form:input>
																						</p>
																					<h4><spring:message code="Rate.Hour"/></h4>
																						<p>
																							<form:input path="rate" id="rate" maxlength="4"  name="rate" class="form-control" type="text"></form:input>
																						</p>
																					<h4><spring:message code="description"/></h4>
																						<p>
																							<form:textarea  style="resize:none;"  path="description" class="form-control" id="description" name="description" rows="3" placeholder=""></form:textarea>
																						</p>
																					</div>
																					</div>
																				</div>
																			</div>
																		<form:input type="hidden" path="planId" value="4" id="planId" name="planId"></form:input>
																		<div class="modal-footer">
																			<button type="button" data-dismiss="modal" class="btn btn-default clearFormFields"><spring:message code="admin.close"/></button>
																			<button type="submit" class="btn blue"><spring:message code="save"/></button>
																		</div>
																		</div>
																	</form:form>																
																</div>
															</div>
														</div>
														<!--end tab-pane-->
													</div>
												</div>
												<!--END TABS-->
											</div>
										</div>
										
									<!-- </div> -->
								</div>
							</div>
						</div>
						<!-- New Dialog end -->
							
<!-- Dialog end -->


<!-- Dialog start -->
						
<div id="editPlan" class="modal fade" tabindex="-1" data-width="460">
	<form:form name="updatePlan" id="form_sample_1111" commandName="dtoManagePlans" method="post" action="saveOrUpdatePlan">
		<div class="form-body">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title"><spring:message code="update"/> <span id="countryUpdate"></span> <spring:message code="student.plan"/></h4>
			</div>
			<div class="alert alert-danger display-hide">
				<button class="close" data-close="alert"></button>
				<spring:message code="form.validation"></spring:message>
			</div>
			<div class="alert alert-success display-hide">
				<button class="close" data-close="alert"></button>
				<spring:message code="form.validation"></spring:message>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
								<h4><spring:message code="Rate.Hour"/> </h4>
								<p>
									<form:input path="rate" id="rateUpdate"  maxlength="4" name="rate" class="form-control" type="text"></form:input>
								</p>
							<h4><spring:message code="hours/month"/></h4>
								<p>
									<form:input path="hours" name="hours" maxlength="4"  id="hoursUpdate" class="form-control" type="text"></form:input>
								</p>
								
								<h4><spring:message code="description"/></h4>
							<p>
								<form:textarea  style="resize:none;"  path="description" class="form-control" id="editdescription" name="description" rows="3" placeholder=""></form:textarea>
							</p>
								
						</div>
					</div>
				</div>
			</div>
		<form:input type="hidden" path="planId" id="planIdNew"></form:input>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-default" ><spring:message code="admin.close"/></button>
					<button type="submit" class="btn blue"><spring:message code="update"/></button>
			</div>
		</div>
	</form:form>
</div>
							
<!-- Dialog end -->


<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="page-footer">
	<div class="page-footer-inner">
		  <spring:message code="copyright" /> TutoríasOnline LLC &copy; 2015
	</div>
	<div class="scroll-to-top">
		<i class="icon-arrow-up"></i>
	</div>
</div>
<!-- END FOOTER -->
<%@ include file="/WEB-INF/views/views-admin/common-js-include.jsp" %>
<script type="text/javascript">
jQuery(document).ready(function() {    
	   Metronic.init(); // init metronic core components
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	   /* TableManaged.init(); */
	   TableEditable.init();
	   FormValidation.init();
});

$(".clearFormFields").click(function(){
	$("#form_sample_1")[0].reset();
	$("#form_sample_pass")[0].reset();
	$("#form_sample_11")[0].reset();
	$("#form_sample_111")[0].reset();
	
	
});

</script>
<script type="text/javascript">
function addNewSubject(){
	$('#addNewPlan').modal('show');
}
function editPlan(planId){
	$("#planIdNew").val(planId);
	var url='<%=request.getContextPath()%>/sys-admin/getPlanByPlanRateId';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		data:{planId:planId},
		success:function(response){
			if(response!=null){
				$("#hoursUpdate").val(response.modelMap.planHours);
				$("#rateUpdate").val(response.modelMap.planRate);
				if(response.modelMap.planCountry=='other'){
					$("#countryUpdate").text('<spring:message code="otherCountries"/>');
				}
				else{
					$("#countryUpdate").text(response.modelMap.planCountry);
				}
				
				$("#editdescription").text(response.modelMap.planDescription);
				
				$('#editPlan').modal('show');
			return true;
		}
		else{
			
		}
		}
	});
	
}
function setDelete(planId){
	var url='<%=request.getContextPath()%>/sys-admin/plan-del';
		$.ajax({
			url:url,
			method:'POST',
			async :false,
			data:{planId:planId},
			success:function(response){
				if(response=="success"){
					
				return true;
			}
			else{
				
			}
			}
		});
}
</script>
<script type="text/javascript">
function navigationUpdate(){
	$("#planMenu").addClass("start active open");
	$("#planSelect").addClass("selected");
	$("#planOpen").addClass("arrow open");
	$("#managePlan").addClass("active");
}
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
var FormValidation = function () {

    // basic validation
    var handleValidation1 = function() {
        // for more info visit the official plugin documentation: 
            // http://docs.jquery.com/Plugins/Validation

            var form1 = $('#form_sample_1');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);

            $.validator.addMethod("matchEmail", function(value, element) {
            	var flag = emailCheck(element);
              	return this.optional(element) || flag;
        	},"Email already exist.");
            
            $.validator.addMethod("phonenumbertype", function(value, element) {
        	  	return this.optional(element) || /^[+0-9]{2,30}$/i.test(value);
        		},"<spring:message code='enteravalidmobilenumber'/>");
            
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input
                messages: {
                    select_multi: {
                        maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
                        minlength: jQuery.validator.format("At least {0} items must be selected")
                    }
                },
                rules: {
                    fname: {
                        minlength: 2,
                        maxlength: 40,
                        required: true
                    },
                    hours: {
                        minlength: 1,
                        maxlength: 4,
                        number: true,
                        required: true
                    },
                    description: {
                        maxlength: 150
                    },
                    rate: {
                        minlength: 1,
                        maxlength: 4,
                        number: true,
                        required: true
                    },
                    fee: {
                    	number: true,
                    	maxlength: 6,
                        required: true
                    },
                    mxnFee: {
                    	number: true,
                    	maxlength: 6,
                        required: true
                    },
                    euroFee: {
                    	number: true,
                    	maxlength: 6,
                        required: true
                    },
                    subjectName: {
                        required: true,
                        maxlength: 40,
                    },
                    lname: {
                        minlength: 2,
                        required: true,
                        maxlength: 40,
                    },
                    dob: {
                        required: true
                    },
                    email: {
                        required: true,
                        maxlength: 80,
                        matchEmail: true,
                        email: true
                    },
                    college: {
                        minlength: 2,
                        required: true,
                        maxlength: 40,
                    },
                    career: {
                        minlength: 2,
                        required: true,
                        maxlength: 40,
                    },
                    password: {
                        minlength: 6,
                        maxlength: 80,
                        required: true
                    },
                    currPass: {
                        minlength: 6,
                        required: true
                    },
                    rpassword: {
                        minlength: 6,
                        //required: true,
                        equalTo: "#submit_form_password"
                    },
                    url: {
                        required: true,
                        url: true
                    },
                    number: {
                        required: true,
                        maxlength: 40,
                        //number: true,
                        phonenumbertype:true
                    },
                    mobileNumber: {
                        required: true,
                       // number: true,
                        maxlength: 40,
                        phonenumbertype:true
                        
                    },
                    contactNumber: {
                        required: true,
                        //number: true,
                        maxlength: 40,
                        phonenumbertype:true
                        
                    },
                    digits: {
                        required: true,
                        digits: true,
                        maxlength: 40
                    },
                    creditcard: {
                        required: true,
                        creditcard: true,
                        maxlength: 20
                    },
                    occupation: {
                        minlength: 5,
                    },
                    select: {
                        required: true
                    },
                    select_multi: {
                        required: true,
                        minlength: 1,
                        maxlength: 3
                    },
                    message: {
                    	required: true
                    },
                    street: {
                    	required: true
                    },
                    city: {
                    	required: true
                    },
                    gpa: {
                    	required: true
                    },
                    gpaScale: {
                    	required: true
                    },
                    newsText:{
                    	required: true,
                    	maxlength: 15
                    },
                    jobText:{
                    	required: true,
                    	maxlength: 15
                    },
                    videoTitle:{
                    	required: true,
                    	maxlength: 15
                    },
                    minutesAdd:{
                    	number: true,
                    	maxlength: 3,
                        required: true
                    },
                    amountAdd:{
                    	number: true,
                    	maxlength: 5,
                        required: true
                    },
                    fromdate:{
                        required: true
                    },
                    todate:{
                        required: true
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit              
                    success1.hide();
                    error1.show();
                    Metronic.scrollTo(error1, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                    success1.show();
                    error1.hide();
                    form[0].submit();
                }
            });


    }
    
    // basic validation
    var handleValidation12 = function() {
        // for more info visit the official plugin documentation: 
            // http://docs.jquery.com/Plugins/Validation

            var form11 = $('#form_sample_11');
            var error11 = $('.alert-danger', form11);
            var success11 = $('.alert-success', form11);

            form11.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input
                messages: {
                    select_multi: {
                        maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
                        minlength: jQuery.validator.format("At least {0} items must be selected")
                    }
                },
                rules: {
                    hours: {
                        minlength: 1,
                        maxlength: 4,
                        number: true,
                        required: true
                    },
                    subjectName: {
                    	maxlength: 40,
                        required: true
                    },
                    description: {
                        maxlength: 150
                    },
                    rate: {
                        minlength: 1,
                        maxlength: 4,
                        number: true,
                        required: true
                    },
                    toId: {
                    	required: true
                    },
                    message: {
                    	required: true
                    },
                    minutesDeduct:{
                    	number: true,
                    	maxlength: 3,
                        required: true
                    },
                    amountDeduct:{
                    	number: true,
                    	maxlength: 5,
                        required: true
                    }
                    
                },

                invalidHandler: function (event, validator) { //display error alert on form submit              
                    success11.hide();
                    error11.show();
                    Metronic.scrollTo(error11, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                    success11.show();
                    error11.hide();
                    form[0].submit();
                }
            });


    }
    
    // basic validation
    var handleValidation121 = function() {
        // for more info visit the official plugin documentation: 
            // http://docs.jquery.com/Plugins/Validation

            var form111 = $('#form_sample_111');
            var error111 = $('.alert-danger', form111);
            var success111 = $('.alert-success', form111);

            form111.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input
                messages: {
                    select_multi: {
                        maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
                        minlength: jQuery.validator.format("At least {0} items must be selected")
                    }
                },
                rules: {
                    hours: {
                        minlength: 1,
                        maxlength: 4,
                        number: true,
                        required: true
                    },
                    description: {
                        maxlength: 150
                    },
                    rate: {
                        minlength: 1,
                        maxlength: 4,
                        number: true,
                        required: true
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit              
                    success111.hide();
                    error111.show();
                    Metronic.scrollTo(error111, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                    success111.show();
                    error111.hide();
                    form[0].submit();
                }
            });


    }
    // basic validation
    var handleValidation1212 = function() {
        // for more info visit the official plugin documentation: 
            // http://docs.jquery.com/Plugins/Validation

            var form1111 = $('#form_sample_1111');
            var error1111 = $('.alert-danger', form1111);
            var success1111 = $('.alert-success', form1111);

            form1111.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input
                messages: {
                    select_multi: {
                        maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
                        minlength: jQuery.validator.format("At least {0} items must be selected")
                    }
                },
                rules: {
                    hours: {
                        minlength: 1,
                        maxlength: 4,
                        number: true,
                        required: true
                    },
                    description: {
                        maxlength: 150
                    },
                    rate: {
                        minlength: 1,
                        maxlength: 4,
                        number: true,
                        required: true
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit              
                    success1111.hide();
                    error1111.show();
                    Metronic.scrollTo(error1111, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                    success1111.show();
                    error1111.hide();
                    form[0].submit();
                }
            });


    }
    
    var passValidation = function() {
        // for more info visit the official plugin documentation: 
            // http://docs.jquery.com/Plugins/Validation

            var formpass = $('#form_sample_pass');
            var errorpass = $('.alert-danger', formpass);
            var successpass = $('.alert-success', formpass);
            
            $.validator.addMethod("matchCurrentPass", function(value, element) {
            	var flag = passCheck(element);
              	return this.optional(element) || flag;
        	},"Your current password does not match.");

            formpass.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input
                messages: {
                    select_multi: {
                        maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
                        minlength: jQuery.validator.format("At least {0} items must be selected")
                    }
                },
                rules: {
                	 subjectName: {
                		 maxlength: 40,
                         required: true
                     },
                     hours: {
                         minlength: 1,
                         maxlength: 4,
                         number: true,
                         required: true
                     },
                     description: {
                         maxlength: 150
                     },
                     rate: {
                         minlength: 1,
                         maxlength: 4,
                         number: true,
                         required: true
                     },
                     fee: {
                    	 number: true,
                         required: true,
                         maxlength: 6
                     },
                	 currPass: {
                         required: true,
                         matchCurrentPass:true
                     },
                	password: {
                        minlength: 5,
                        required: true,
                        maxlength: 80,
                    },
                    rpassword: {
                        minlength: 5,
                        required: true,
                        equalTo: "#submit_form_password"
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit              
                	successpass.hide();
                    errorpass.show();
                    Metronic.scrollTo(errorpass, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                	successpass.show();
                    errorpass.hide();
                    form[0].submit();
                }
            });


    }
    
    var passCheck = function() {
    	var currPass=$("#currPass").val();
    	var flag = false;
    	var url=contextPath+'/sys-admin/passCheck';
    	$.ajax({
    		url:url,
    		method:'GET',
    		async :false,
    		data:{currPass:currPass},
    		success:function(response){
    		if(response=="error"){
    			flag = false;
    			}
    		else{
    			flag = true;
    		}
    		}
    		
    	});  
    	return flag;
    }
    var emailCheck = function() {
    	var email=$("#email").val();
    	var flag = false;
    	var userEmail=$("#userEmail").val();
    	
    	if(userEmail==email){
    		flag=true;
    	}
    	else{
    	var url=contextPath+'/sys-admin/emailCheck';
    	$.ajax({
    		url:url,
    		method:'GET',
    		async :false,
    		data:{email:email},
    		success:function(response){
    		if(response=="error"){
    			flag = false;
    			}
    		else{
    			flag = true;
    		}
    		},
    		cache: false
    	});  
    	}
    	return flag;
    }

    // validation using icons
    var handleValidation2 = function() {
        // for more info visit the official plugin documentation: 
            // http://docs.jquery.com/Plugins/Validation

            var form2 = $('#form_sample_2');
            var error2 = $('.alert-danger', form2);
            var success2 = $('.alert-success', form2);

            form2.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input
                rules: {
                	 fname: {
                         minlength: 2,
                         required: true
                     },
                     lname: {
                         minlength: 2,
                         required: true
                     },
                    email: {
                        required: true,
                        email: true
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    url: {
                        required: true,
                        url: true
                    },
                    number: {
                        required: true,
                        number: true
                    },
                    digits: {
                        required: true,
                        digits: true
                    },
                    creditcard: {
                        required: true,
                        creditcard: true
                    },
                },

                invalidHandler: function (event, validator) { //display error alert on form submit              
                    success2.hide();
                    error2.show();
                    Metronic.scrollTo(error2, -200);
                },

                errorPlacement: function (error, element) { // render error placement for each input type
                    var icon = $(element).parent('.input-icon').children('i');
                    icon.removeClass('fa-check').addClass("fa-warning");  
                    icon.attr("data-original-title", error.text()).tooltip({'container': 'body'});
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group   
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    
                },

                success: function (label, element) {
                    var icon = $(element).parent('.input-icon').children('i');
                    $(element).closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
                    icon.removeClass("fa-warning").addClass("fa-check");
                },

                submitHandler: function (form) {
                    success2.show();
                    error2.hide();
                    form[0].submit(); // submit the form
                }
            });


    }

    // advance validation
    var handleValidation3 = function() {
        // for more info visit the official plugin documentation: 
        // http://docs.jquery.com/Plugins/Validation

            var form3 = $('#form_sample_3');
            var error3 = $('.alert-danger', form3);
            var success3 = $('.alert-success', form3);

            //IMPORTANT: update CKEDITOR textarea with actual content before submit
            form3.on('submit', function() {
                for(var instanceName in CKEDITOR.instances) {
                    CKEDITOR.instances[instanceName].updateElement();
                }
            })

            form3.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "", // validate all fields including form hidden input
                rules: {
                    name: {
                        minlength: 2,
                        required: true
                    },
                    hours: {
                        minlength: 1,
                        maxlength: 4,
                        number: true,
                        required: true
                    },
                    rate: {
                        minlength: 1,
                        maxlength: 4,
                        number: true,
                        required: true
                    },
                    email: {
                        required: true,
                        email: true
                    },  
                    options1: {
                        required: true
                    },
                    options2: {
                        required: true
                    },
                    select2tags: {
                        required: true
                    },
                    datepicker: {
                        required: true
                    },
                    occupation: {
                        minlength: 5,
                    },
                    membership: {
                        required: true
                    },
                    service: {
                        required: true,
                        minlength: 2
                    },
                    markdown: {
                        required: true
                    },
                    editor1: {
                        required: true
                    },
                    editor2: {
                        required: true
                    }
                },

                messages: { // custom messages for radio buttons and checkboxes
                    membership: {
                        required: "Please select a Membership type"
                    },
                    service: {
                        required: "Please select  at least 2 types of Service",
                        minlength: jQuery.validator.format("Please select  at least {0} types of Service")
                    }
                },

                errorPlacement: function (error, element) { // render error placement for each input type
                    if (element.parent(".input-group").size() > 0) {
                        error.insertAfter(element.parent(".input-group"));
                    } else if (element.attr("data-error-container")) { 
                        error.appendTo(element.attr("data-error-container"));
                    } else if (element.parents('.radio-list').size() > 0) { 
                        error.appendTo(element.parents('.radio-list').attr("data-error-container"));
                    } else if (element.parents('.radio-inline').size() > 0) { 
                        error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
                    } else if (element.parents('.checkbox-list').size() > 0) {
                        error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
                    } else if (element.parents('.checkbox-inline').size() > 0) { 
                        error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
                    } else {
                        error.insertAfter(element); // for other inputs, just perform default behavior
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit   
                    success3.hide();
                    error3.show();
                    Metronic.scrollTo(error3, -200);
                },

                highlight: function (element) { // hightlight error inputs
                   $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                    success3.show();
                    error3.hide();
                    form[0].submit(); // submit the form
                }

            });

             //apply validation on select2 dropdown value change, this only needed for chosen dropdown integration.
            $('.select2me', form3).change(function () {
                form3.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input
            });

            // initialize select2 tags
            $("#select2_tags").change(function() {
                form3.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input 
            }).select2({
                tags: ["red", "green", "blue", "yellow", "pink"]
            });

            //initialize datepicker
            $('.date-picker').datepicker({
                rtl: Metronic.isRTL(),
                autoclose: true
            });
            $('.date-picker .form-control').change(function() {
                form3.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input 
            })
    }

    var handleWysihtml5 = function() {
        if (!jQuery().wysihtml5) {
            
            return;
        }

        if ($('.wysihtml5').size() > 0) {
            $('.wysihtml5').wysihtml5({
                "stylesheets": ["../../assets/global/plugins/bootstrap-wysihtml5/wysiwyg-color.css"]
            });
        }
    }

    return {
        //main function to initiate the module
        init: function () {

            handleWysihtml5();
            handleValidation1();
            handleValidation2();
            handleValidation3();
            passValidation();
            handleValidation12();
            handleValidation121();
            handleValidation1212();
            passCheck();
            emailCheck();
            

        }

    };

}();
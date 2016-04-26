<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="es">

<head>
	<%@include file="fragJSP/metacabecera.jspf"%>
    <title>Sistema de Reservas - Carga Inicial</title>

	<%@include file="fragJSP/estilos.jspf"%>

</head>


<body class="nav-md">
	<span id="primeraCarga"></span>
    <div class="container body">


        <div class="main_container">

            <div class="col-md-3 left_col">
                <div class="left_col scroll-view">

                    <div class="navbar nav_title" style="border: 0;">
                        <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>MiNegocio</span></a>
                    </div>
                    <div class="clearfix"></div>


                    <!-- menu prile quick info -->
                    <div class="profile">
                        <div class="profile_pic">
                            <img src="<c:url value="/resources/images/img.jpg" />" alt="..." class="img-circle profile_img">
                        </div>
                        <div class="profile_info">
                            <span>Bienvenido,</span>
                            <h2>Usuario Sin Registrar</h2>
                        </div>
                    </div>
                    <!-- /menu prile quick info -->

                    <br />

                    <!-- sidebar menu -->
                    <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">


                    </div>
                    <!-- /sidebar menu -->

                    <!-- /menu footer buttons -->
                    <div class="sidebar-footer hidden-small">

                    </div>
                    <!-- /menu footer buttons -->
                </div>
            </div>

            <!-- top navigation -->
            <div class="top_nav">

                <div class="nav_menu">
                    <nav class="" role="navigation">
                        <div class="nav toggle">
                            <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                        </div>

                        <ul class="nav navbar-nav navbar-right">
     
                        </ul>
                    </nav>
                </div>

            </div>
            <!-- /top navigation -->

            <!-- page content -->
            <div class="right_col" role="main">

                <div class="">
                    <div class="page-title">
                        <div class="title_left">
                            <h3>Configuración del negocio</h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>

                    <div class="row">

                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                	<div id="estadoFormulario"></div>
                                    <h2>Datos iniciales</h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">


                                    <!-- Smart Wizard -->
                                    <p>Es necesario introducir los siguientes datos para poder empezar.</p>
                                    <div id="wizard" class="form_wizard wizard_horizontal">
                                        <ul class="wizard_steps">
                                            <li>
                                                <a id="step1" href="#step-1">
                                                    <span class="step_no">1</span>
                                                    <span class="step_descr">
                                            Datos del negocio<br />
                                            <small>Información básica</small>
                                        </span>
                                                </a>
                                            </li>
                                            <li>
                                                <a id="step2" href="#step-2">
                                                    <span class="step_no">2</span>
                                                    <span class="step_descr">
                                            Datos de acceso del administrador<br />
                                            <small>Usuario y contraseña</small>
                                        </span>
                                                </a>
                                            </li>
                                            <li>
                                                <a id="step3" href="#step-3">
                                                    <span class="step_no">3</span>
                                                    <span class="step_descr">
                                            Datos personales del administrador<br />
                                            <small>Nombre, apellidos...</small>
                                        </span>
                                                </a>
                                            </li>

                                        </ul>
                                        <div id="step-1">
                                            <form id="formNegocio" class="form-horizontal form-label-left">

                                                <div class="form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="nombre-negocio">Nombre del negocio <span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <input type="text" name="nombre-negocio" id="nombre-negocio" required="required" class="form-control col-md-7 col-xs-12">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="direccion-negocio">Dirección<span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <input type="text" id="direccion-negocio" name="direccion-negocio" required="required" class="form-control col-md-7 col-xs-12">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="telefono-negocio" class="control-label col-md-3 col-sm-3 col-xs-12">Teléfono <span class="required">*</span></label>
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <input id="telefono-negocio" class="form-control col-md-7 col-xs-12" type="text" name="telefono-negocio">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Url de promoción (Facebook, Twitter...)
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <input id="url-negocio" class="form-control col-md-7 col-xs-12" type="text" name="url-negocio">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Tipo de negocio
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <input id="tipo-negocio" class="form-control col-md-7 col-xs-12" type="text" name="tipo-negocio">
                                                    </div>
                                                </div>
                                                
                                            </form>

                                        </div>
                                        <div id="step-2">
                                            <form id="formUsuario" class="form-horizontal form-label-left">

                                                <div class="form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email-admin">Email del administrador <span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <input type="text" name="email-admin" id="email-admin" required="required" class="form-control col-md-7 col-xs-12">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password-admin">Contraseña del administrador<span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <input type="password" id="password-admin" name="password-admin" required="required" class="form-control col-md-7 col-xs-12">
                                                    </div>
                                                </div>                                                                          
                                            </form>
                                        </div>
                                        <div id="step-3">
                                            <form id="formPersona" class="form-horizontal form-label-left">
                                                <div class="form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="nombre-admin">Nombre del administrador <span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <input type="text" name="nombre-admin" id="nombre-admin" required="required" class="form-control col-md-7 col-xs-12">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="apellidos-admin">Apellidos del administrador<span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <input type="text" id="apellidos-admin" name="apellidos-admin" required="required" class="form-control col-md-7 col-xs-12">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="dni-admin" class="control-label col-md-3 col-sm-3 col-xs-12">DNI <span class="required">*</span></label>
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <input id="dni-admin" class="form-control col-md-7 col-xs-12" type="text" name="dni-admin" required="required">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Fecha de nacimiento
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <input id="fecha-nacimiento" class="form-control col-md-7 col-xs-12" type="date" name="fecha-nacimiento">
                                                    </div>
                                                </div>                                                
                                            </form>
                                        </div>


                                    </div>
                                    <!-- End SmartWizard Content -->

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                
                <!-- footer content -->
            <footer>
                <div class="">
                    <p class="pull-right">Gentelella Alela! a Bootstrap 3 template by <a>Kimlabs</a>. |
                        <span class="lead"> <i class="fa fa-paw"></i> Gentelella Alela!</span>
                    </p>
                </div>
                <div class="clearfix"></div>
            </footer>
            <!-- /footer content -->
                
            </div>
            <!-- /page content -->
        </div>

    </div>

    <div id="custom_notifications" class="custom-notifications dsp_none">
        <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
        </ul>
        <div class="clearfix"></div>
        <div id="notif-group" class="tabbed_notifications"></div>
    </div>

    <%@include file="fragJSP/javascripts.jspf"%>
    
    <script type="text/javascript">
        $(document).ready(function () {
            // Smart Wizard 	
            $('#wizard').smartWizard();

            function onFinishCallback() {
                $('#wizard').smartWizard('showMessage', 'Finish Clicked');
                //alert('Finish Clicked');
            }
        });

        $(document).ready(function () {
            // Smart Wizard	
            $('#wizard_verticle').smartWizard({
                transitionEffect: 'slide'
            });

        });
        
    </script>
    <script type="text/javascript">
    	console.log("aquii");
    	  	
    </script>

</body>

</html>
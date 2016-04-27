<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Principal</title>

	<%@include file="fragJSP/estilos.jspf"%>

</head>


<body class="nav-md">

    <div class="container body">


        <div class="main_container">

            <div class="col-md-3 left_col">
                <div class="left_col scroll-view">

					<%@include file="fragJSP/menuPerfil.jspf"%>

                    <%@include file="fragJSP/menuLateral.jspf"%>
                </div>
            </div>

            <!-- top navigation -->
            <div class="top_nav">

                <div class="nav_menu">
					<%@include file="fragJSP/menuAcciones.jspf"%>
                </div>

            </div>
            <!-- /top navigation -->

            <!-- page content -->
            <div class="right_col" role="main">

                <div class="">
                    <div class="page-title">
                        <div class="title_left">
                            <h3>Bienvenido</h3>
                        </div>

                    </div>
                    <div class="clearfix"></div>

                    <div class="row">

                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
				                <div class="x_title">
				                  <h2>Listado de empleados</h2>
				                  <div class="clearfix"></div>
				                </div>
                                <div class="x_content">
					              <table class="table table-striped">
					                    <thead>
					                      <tr>
					                        <th>id</th>
					                        <th>Nivel</th>
					                        <th>Email</th>
					                        <th>Nombre</th>
					                        <th>Apellidos</th>
					                        <th>DNI</th>
					                        <th>Fecha nacimiento</th>
					                        <th></th>
					                        <th></th>
					                      </tr>
					                    </thead>
					                    <tbody>
					                      <tr>
					                        <th scope="row">1</th>
					                        <td>Admin</td>
					                        <td>Mark</td>
					                        <td>Otto</td>
					                        <td>@mdo</td>
					                        <td>66666666Z</td>
					                        <td>06/06/1986</td>
					                        <td>Ver</td>
					                        <td>Borrar</td>
					                      </tr>
					                    </tbody>
					                  </table>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                               
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

        });

    </script>

</body>

</html>
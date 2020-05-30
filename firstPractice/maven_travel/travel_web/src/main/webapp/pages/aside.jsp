<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!-- 导航侧栏 -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="../img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p><security:authentication property="principal.username"/></p>
                <a><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>

        <!-- 菜单Start -->

        <ul class="sidebar-menu">
            <li class="header">菜单</li>

            <li id="admin-index"><a href="${pageContext.request.contextPath}/pages/admin.jsp"><i class="fa fa-home"></i> <span>首页</span></a></li>

            <!-- 菜单 -->



            <li class="treeview">
                <a href="#">
                    <i class="fa fa-folder"></i> <span>数据管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li id="admin-datalist">
                        <a href="${pageContext.request.contextPath}/order/findAll?page=1&size=5">
                            <i class="fa fa-circle-o"></i> 订单管理
                        </a>
                    </li>
                    <security:authorize access="hasRole('ADMIN')">
                        <li id="product-list">
                            <a href="${pageContext.request.contextPath}/product/findAll?page=1&size=5">
                                <i class="fa fa-circle-o"></i> 商品管理
                            </a>
                        </li>
                    </security:authorize>

                </ul>
            </li>
            <security:authorize access="hasRole('ADMIN')">
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-cogs"></i> <span>系统管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">


                    <li id="admin-userList">
                        <a href="${pageContext.request.contextPath}/user/findAll">
                            <i class="fa fa-circle-o"></i> 用户管理
                        </a>
                    </li>
                    <li id="role-list">
                        <a href="${pageContext.request.contextPath}/role/findAll">
                            <i class="fa fa-circle-o"></i> 角色管理
                        </a>
                    </li>
                    <li id="permission-list">
                        <a href="${pageContext.request.contextPath}/permission/findAll">
                            <i class="fa fa-circle-o"></i> 资源权限管理
                        </a>
                    </li>


                </ul>
                </security:authorize>
            </li>



            <!-- 菜单end /-->



        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
<!-- 导航侧栏 /-->

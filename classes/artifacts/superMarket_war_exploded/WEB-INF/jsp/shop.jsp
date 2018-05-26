<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 18/4/23
  Time: 下午4:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="right" >
    <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>订单扫描页面</span>
    </div>
    <div>
        <h1>无人超市订单扫描模块</h1>
    </div>
    <div>
        <table >
            <tr>
                <th>商品名称</th>
                <th>商品价格</th>
                <th>供应商</th>
                <th>库存</th>
                <th>操作时间</th>
                <th>操作</th>
            </tr>
            <tr>
                <td>金士顿U盘</td>
                <td>70</td>
                <td>红色</td>
                <td>482</td>
                <td>100%</td>
                <td align="center">
                    <input type="button" value="加入购物车" onclick="add_shoppingcart(this);"/>
                </td>
            </tr>
        </table>
    </div>
    <div></div>
    <div>
        <h1>购物车</h1>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <th>商品</th>
                <th>单价(元)</th>
                <th>数量</th>
                <th>金额(元)</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody id="goods">
            </tbody>
            <tfoot>
            <tr>
                <td colspan="3" align="right">总计</td>
                <td id="total"></td>
                <td></td>
            </tr>
            </tfoot>
        </table>
        <div>

            <input type="submit" value="提交订单" onclick="commit()" style=" position: absolute;right: 20px;"/>
        </div>
    </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/shop.js" charset="utf-8"></script>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
         <%--pageEncoding="UTF-8"%>--%>
<%--<%@include file="/WEB-INF/jsp/common/head.jsp"%>--%>

<%--<div class="right">--%>
    <%--<div class="location">--%>
        <%--<strong>你现在所在的位置是:</strong>--%>
        <%--<span>订单管理页面</span>--%>
    <%--</div>--%>
    <%--<!--账单表格 样式和供应商公用-->--%>
    <%--<table class="providerTable" cellpadding="0" cellspacing="0">--%>
        <%--<tr class="firstTr">--%>
            <%--<th width="10%">订单编码</th>--%>
            <%--<th width="20%">商品名称</th>--%>
            <%--<th width="10%">供应商</th>--%>
            <%--<th width="10%">商品单价</th>--%>
            <%--<th width="10%">商品库存</th>--%>
            <%--<th width="10%">创建时间</th>--%>
            <%--<th width="30%">操作</th>--%>
        <%--</tr>--%>
        <%--&lt;%&ndash;<c:forEach var="bill" items="${billList }" varStatus="status">&ndash;%&gt;--%>
            <%--<tr>--%>
                <%--<td>--%>
                    <%--<span>${bill.billCode }</span>--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<span>${bill.productName }</span>--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<span>${bill.providerName}</span>--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<span>${bill.totalPrice}</span>--%>
                <%--</td>--%>
                <%--<td>--%>
					<%--<span>--%>
                        <%--${bill.totalPrice}--%>
					<%--</span>--%>
                <%--</td>--%>
                <%--<td>--%>
					<%--<span>--%>
					<%--<fmt:formatDate value="${bill.creationDate}" pattern="yyyy-MM-dd"/>--%>
					<%--</span>--%>
                <%--</td>--%>
                <%--<td align="center">--%>
                    <%--<input type="button" value="加入购物车" onclick="add_shoppingcart(this);"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
    <%--</table>--%>
    <%--&lt;%&ndash;<div>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<table>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<thead>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>商品</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>单价(元)</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>数量</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>金额(元)</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>删除</th>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</thead>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<tbody id="goods">&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tbody>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<tfoot>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td colspan="3" align="right">总计</td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td id="total"></td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td></td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tfoot>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</table>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--</div>--%>
<%--</section>--%>

<%--<%@include file="/WEB-INF/jsp/common/foot.jsp" %>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/shop.js" charset="utf-8"></script>--%>



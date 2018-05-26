//加入购物车
//调用是传入了this，它只带你正点击的那个元素
//给方法加参数接收该值，参数名不能是关键字
//WebSocket ws=new WebSocket();
function add_shoppingcart(btn){
    //获取按钮的爷爷(tr)
    var tr = btn.parentNode.parentNode;
    //获取爷爷的孩子们
    var tds = tr.getElementsByTagName("td");
    //获取第0个td的内容（商品名）
    var name = tds[0].innerHTML;
    //获取第一个td的内容（单价）
    var price = tds[1].innerHTML;
    console.log(name+","+price);
    //创建新的行
    var ntr = document.createElement("tr");
    //给新的行设置内容
    ntr.innerHTML =
        '<td id="thingName">'+name+'</td>'+
        '<td>'+price+'</td>'+
        '<td align="center">'+
        '<input type="button" value="-" onclick="decrease(this)"/> '+
        '<input type="text" size="3" readonly value="1" id="orderNum"/> '+
        '<input type="button" value="+" onclick="increase(this)"/>'+
        '</td>'+
        '<td>'+price+'</td>'+
        '<td align="center"><input type="button" value="x" onclick="removeshop(this)"/></td>'
    //将新的行追加到tbody下
    var tbody =
        document.getElementById("goods");
    tbody.appendChild(ntr);
    sum();
}
function removeshop(btn) {
    var tr = btn.parentNode.parentNode;
    tr.parentNode.removeChild(tr);
    sum();

}
//增加购物车数量的函数
function increase(btn){
    var td2 = btn.parentNode;
    var text = td2.getElementsByTagName("input")[1];
    text.value++;
    var tds = td2.parentNode.getElementsByTagName("td");
    //获取td1的内容（单价）
    var price = tds[1].innerHTML;
    //计算金额
    var money = text.value*price;
    //将金额写入td3
    tds[3].innerHTML = money;
    sum();
}
//减少购物车数量的函数
function decrease(btn){
    var td2 = btn.parentNode;
    var text = td2.getElementsByTagName("input")[1];
    text.value--;
    if(text.value==0)
        removeshop(btn);
    var tds = td2.parentNode.getElementsByTagName("td");
    //获取td1的内容（单价）
    var price = tds[1].innerHTML;
    //计算金额
    var money = text.value*price;
    //将金额写入td3
    tds[3].innerHTML = money;
    sum();
}
function sum(){
    //获取tbody内所有的行
    var tbody = document.getElementById("goods");
    var trs = tbody.getElementsByTagName("tr");
    //遍历这些行
    var s = 0;
    for(var i=0;i<trs.length;i++){
        //获取本行第4个td
        var td3 = trs[i].getElementsByTagName("td")[3];
        //将td3的内容（金额）进行累加
        s += parseFloat(td3.innerHTML);
    }
    //将合计值写入对应的td
    var td = document.getElementById("total");
    td.innerHTML = s;
}
function commit(){
    var num=$("#orderNum").val();
    if(num==null){
        alert("请将商品加入购物车");
        return ;
    }
    alert("操作成功");

}
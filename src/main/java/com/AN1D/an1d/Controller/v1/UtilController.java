package com.AN1D.an1d.controller.v1;
import com.AN1D.an1d.Repository.OrderDao;
import com.AN1D.an1d.Utils.OrderIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("v1/")
public class UtilController {

    @Autowired 
    private OrderDao orderDao;

    @GetMapping(value = "get-next-order-id")
    public String getNextOrderId() {
        String last_order_id = orderDao.findLastCreatedOrder();
        int last_created_order = Integer.parseInt(last_order_id.substring(last_order_id.length() - 4));
        return OrderIdUtil.createOrderId(last_created_order);
    }
    
}

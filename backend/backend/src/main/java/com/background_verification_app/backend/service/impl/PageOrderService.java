package com.background_verification_app.backend.service.impl;

import com.background_verification_app.backend.dto.Response;
import com.background_verification_app.backend.entity.PageOrder;
import com.background_verification_app.backend.exception.OwnException;
import com.background_verification_app.backend.repo.PageOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PageOrderService {

    @Autowired
    private PageOrderRepository pageOrderRepository;

    public Response savePageOrder(List<String> pageNames) {

        Response response = new Response();

        try {
            int latestOrderId = pageOrderRepository.findMaxPageOrderId().orElse(0) + 1;
            List<PageOrder> pageOrders = new ArrayList<>();
            for (int i = 0; i < pageNames.size(); i++) {
                PageOrder order = new PageOrder();
                order.setPageOrderId(latestOrderId);
                order.setPagePosition(i);
                order.setPageName(pageNames.get(i));
                pageOrders.add(order);
            }
            pageOrderRepository.saveAll(pageOrders);

            response.setStatusCode(200);
            response.setMessage("Successful Saved Pages");

        } catch (OwnException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Saving pages : " + e.getMessage());
        }
        return response;
    }

    // Get page order by page_order_id
    public List<String> getPageOrderByPageOrderId(int pageOrderId) {
        return pageOrderRepository.findPageOrder(pageOrderId);
    }

}

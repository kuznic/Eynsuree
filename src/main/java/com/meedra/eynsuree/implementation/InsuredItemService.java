package com.meedra.eynsuree.implementation;

import com.meedra.eynsuree.model.InsuredItem;
import com.meedra.eynsuree.repository.CustomerRepository;
import com.meedra.eynsuree.repository.InsuredItemRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class InsuredItemService {

    @Autowired
    InsuredItemRepository insuredItemRepository;

    @Autowired
    CustomerRepository customerRepository;



    public List<InsuredItem> fetchInsuredItems(String customerEmail) throws NoSuchFieldException, NotFoundException {

        Supplier<NoSuchFieldException> s = () -> new NoSuchFieldException("No Customer found");
        Supplier<NotFoundException> t = () -> new NotFoundException("No Insured Item found");

        var customer = customerRepository.
                findByEmail(customerEmail)
                .orElseThrow(s);

        return insuredItemRepository.
                findByCustomer(customer)
                .orElseThrow(t);
    }

    public InsuredItem fetchInsuredItem(UUID insuredItemUid) throws NoSuchFieldException {

        Supplier<NoSuchFieldException> s = () -> new NoSuchFieldException("No Insured itemfound");

        return insuredItemRepository
                .findByUid(insuredItemUid)
                .orElseThrow(s);
    }

    @Transactional
    public void updateInsuredItem(Long insuredItemId, HashMap<String,String> insuredItemMap) throws NoSuchFieldException {
        Supplier<NoSuchFieldException> s = () -> new NoSuchFieldException("No Insured itemfound");

        var insuredItem = insuredItemRepository
                .findById(insuredItemId)
                .orElseThrow(s);

        insuredItem.setNextPaymentDate(insuredItem.getPaymentDate().plusDays(30));

        insuredItem.setInsuredItem(insuredItemMap);

        insuredItemRepository.save(insuredItem);


    }
}

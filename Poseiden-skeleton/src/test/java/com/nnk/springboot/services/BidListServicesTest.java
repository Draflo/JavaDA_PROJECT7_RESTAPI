package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@WebMvcTest(BidListService.class)
public class BidListServicesTest {
	
	@MockBean
	private BidListRepository bidListRepository;
	
	@Autowired
	private BidListService bidListService;
	
	private static BidList bidList = new BidList();
	
	@BeforeEach
	private void init() {
		bidList.setBidListId(1);
		bidList.setAccount("Test");
		bidList.setType("Test");
        bidList.setBidQuantity(10.0);
	}
	

	@Test
	public final void testFindById() {
		System.out.println(bidList);
		when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));
		BidList foundBidList = bidListService.findById(1);
		assertThat(foundBidList).isEqualTo(bidList);
		
	}
	
	@Test
	public final void testFindByIdNotFound() throws EntityNotFoundException {
		assertThrows(EntityNotFoundException.class, () -> bidListService.findById(0));
	}
	
	@Test
	public final void testDeleteById() {
		when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));
		bidListService.deleteById(1);
		verify(bidListRepository).deleteById(1);
	}
	
	@Test
	public final void testFindAll() {
		BidList bidList = new BidList();
		bidList.setBidListId(10);
		bidList.setAccount("Test");
		bidList.setType("Test");
        bidList.setBidQuantity(10.0);
		List<BidList> findAll = new ArrayList<>();
		findAll.add(bidList);
		when(bidListRepository.findAll()).thenReturn(findAll);
		List<BidList> foundBidList = bidListService.findAll();
		assertThat(foundBidList).isEqualTo(findAll);
	}

}

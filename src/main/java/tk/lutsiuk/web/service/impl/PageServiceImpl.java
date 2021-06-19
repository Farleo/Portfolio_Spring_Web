package tk.lutsiuk.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.lutsiuk.web.models.Page;
import tk.lutsiuk.web.repository.PageRepository;
import tk.lutsiuk.web.service.PageService;

@Service
public class PageServiceImpl implements PageService {
	
	@Autowired
	private PageRepository pageRepository;
	
	@Override
	public Page findById(Long id) {
		return pageRepository.getOne(id);
	}
	
	@Override
	public Page findByTitle(String title) {
		return pageRepository.findByTitle(title);
	}
	
	@Override
	public void updatePageById(Page page) {
		pageRepository.save(page);
	}
}

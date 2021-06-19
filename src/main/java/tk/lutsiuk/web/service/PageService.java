package tk.lutsiuk.web.service;

import tk.lutsiuk.web.models.Page;

public interface PageService {
	Page findById (Long id);
	Page findByTitle (String title);
	void updatePageById(Page page);
}

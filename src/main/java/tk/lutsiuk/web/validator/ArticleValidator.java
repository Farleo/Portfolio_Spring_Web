package tk.lutsiuk.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tk.lutsiuk.web.models.Article;
import tk.lutsiuk.web.repository.ArticleRepository;

@Service
public class ArticleValidator implements Validator {
	@Autowired
	ArticleRepository articleRepository;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Article.class.equals(aClass);
	}
	
	@Override
	public void validate(Object o, Errors errors) {
		Article article = (Article) o;
		Article byTitle = articleRepository.findByTitle(article.getTitle());
		
		if (byTitle != null && !article.getId().equals(byTitle.getId())) {
			errors.rejectValue("title", "title.unique", "This title already exist");
		}
		if (article.getTitle().isEmpty()) {
			errors.rejectValue("title", "title.unique", "Title is empty");
		}
		if (article.getInfo().equals("<div><br></div>") || article.getInfo().isEmpty()) {
			errors.rejectValue("info", "info.unique", "Info is empty");
		}
		if (article.getFullText().equals("<div><br></div>") || article.getFullText().isEmpty()) {
			errors.rejectValue("fullText", "fulltext.unique", "Full Text is empty");
		}
		
	}
}

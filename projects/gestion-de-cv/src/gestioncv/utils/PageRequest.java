package gestioncv.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageRequest {

//	@Min(0)
	private int firstResult;

//	@Size(min = 0, max = 1000)
	private int maxResultsPerPage;
}

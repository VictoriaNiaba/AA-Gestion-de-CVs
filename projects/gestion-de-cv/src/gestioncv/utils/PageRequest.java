package gestioncv.utils;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PageRequest {

	@Min(0)
	private int firstResult;

	@Size(min = 0, max = 1000)
	private int maxResultsPerPage;
}

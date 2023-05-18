package in.bushansirgur.expensetrackerapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.bushansirgur.expensetrackerapi.entity.Expense;
import in.bushansirgur.expensetrackerapi.service.ExpenseService;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@GetMapping("/expenses")
	public List<Expense> getAllExpenses() {
		return expenseService.getAllExpenses();
	}

//	@GetMapping("/expenses")
//	public ResponseEntity<List<Expense>> get() {
//
//		List<Expense> allExpenses = expenseService.getAllExpenses();
//		return new ResponseEntity<List<Expense>>(allExpenses, HttpStatus.OK);
//
//	}

	@GetMapping("/expenses/{id}")
	public Expense getExpenseById(@Valid @PathVariable Long id) {
		return expenseService.getExpenseById(id);
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/expenses")
	public void deleteExpenseById(@RequestParam Long id) {
		expenseService.deleteExpenseById(id);
	}

	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/expenses")
	public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
		return expenseService.saveExpenseDetails(expense);
	}

	@PutMapping("/expenses/{id}")
	public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
		return expenseService.updateExpenseDetails(id, expense);
	}

}

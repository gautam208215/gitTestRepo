package in.bushansirgur.expensetrackerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bushansirgur.expensetrackerapi.entity.Expense;
import in.bushansirgur.expensetrackerapi.exception.ResourceNotFoundException;
import in.bushansirgur.expensetrackerapi.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepo;
	
	@Override
	public List<Expense> getAllExpenses() {
		return expenseRepo.findAll();
	}

	@Override
	public Expense getExpenseById(Long id) {
		Optional<Expense> expense = expenseRepo.findById(id);
		if (expense.isPresent()) {
			return expense.get();
		}
		throw new ResourceNotFoundException("Expense is not found for the id "+id);
	}

	@Override
	public void deleteExpenseById(Long id) {
		expenseRepo.deleteById(id);
	}

	@Override
	public Expense saveExpenseDetails(Expense expense) {
		return expenseRepo.save(expense);
	}

	@Override
	public Expense updateExpenseDetails(Long id, Expense expense) {
		Expense existingExpense = getExpenseById(id);
		existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
		existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
		existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
		existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
		existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
		log.info("existing expense got " + existingExpense);
		return expenseRepo.save(existingExpense);
	}

}




























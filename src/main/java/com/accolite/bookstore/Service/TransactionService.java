package com.accolite.bookstore.Service;

import com.accolite.bookstore.Model.Transactions;
import com.accolite.bookstore.Repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public List<Transactions> getAllTransactions(){
        return this.transactionRepo.findAll();
    }

    public List<Transactions> getAllTransactionsOfUser(long userId){
        Optional<List<Transactions>> transactions = this.transactionRepo.findByUserIdEquals(userId);
        if(transactions.isPresent()){
            return transactions.get();
        }else{
            throw new NullPointerException();
        }
    }

    public Transactions addTransactionByDetail(long userId, long walletId, int bookId, double amount, String status){
        Transactions tr = new Transactions();
        tr.setUserId(userId);
        tr.setWalletId(walletId);
        tr.setBookId(bookId);
        tr.setAmount(amount);
        tr.setStatus(status);
        return this.transactionRepo.save(tr);
    }

    public Transactions addFirstTransaction(long wallet_id, long user_id, String status){
        Transactions t = new Transactions();
        t.setUserId(user_id);
        t.setBookId(0);
        t.setWalletId(wallet_id);
        t.setAmount(0);
        t.setStatus(status);
        return this.transactionRepo.save(t);
    }
}

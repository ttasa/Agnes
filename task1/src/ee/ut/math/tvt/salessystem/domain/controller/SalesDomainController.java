package ee.ut.math.tvt.salessystem.domain.controller;

import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {

    /**
     * Load the current state of the warehouse.
     * 
     * @return List of ${link
     *         ee.ut.math.tvt.salessystem.domain.data.StockItem}s.
     */
    public List<StockItem> loadWarehouseState();

    public List<Sale> loadHistoryState();

    // business processes
    /**
     * Initiate new business transaction - purchase of the goods.
     * 
     * @throws VerificationFailedException
     */
    public void startNewPurchase() throws VerificationFailedException;

    /**
     * Rollback business transaction - purchase of goods.
     * 
     * @throws VerificationFailedException
     */
    public void cancelCurrentPurchase() throws VerificationFailedException;

    /**
     * Commit business transaction - purchase of goods.
     * 
     * @param goods
     *            Goods that the buyer has chosen to buy.
     * @throws VerificationFailedException
     */
    public void submitCurrentPurchase(Sale sale)
            throws VerificationFailedException;

    /**
     * Initiates the warehouse item adding
     * 
     * @throws VerificationFailedException
     */
    public void startNewAdd() throws VerificationFailedException;
    
    /**
     * Adds the selected item to the warehouse
     * 
     * @throws VerificationFailedException
     */
    public void confirmItemAdd(StockItem item) throws VerificationFailedException;
    
    /**
     * Rollback business transaction - purchase of goods.
     * 
     * @throws VerificationFailedException
     */
    public void cancelItemAdd() throws VerificationFailedException;
    
    public void endSession();
    
}
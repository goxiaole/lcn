package txtransactional.transactional;

import lombok.Data;

@Data
public class TxTransaction {
    private String groupId;
    private String transactionId;
    private TransactionType transactionType;
}

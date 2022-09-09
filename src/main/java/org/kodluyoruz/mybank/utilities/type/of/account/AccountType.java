package org.kodluyoruz.mybank.utilities.type.of.account;


public enum AccountType {

        ACCUMULATION_ACCOUNT(1), CURRENT_ACCOUNT(2);
        private int type;
        AccountType(int type){
            this.type = type;
        }

        public int getType(){
            return this.type;
        }
}



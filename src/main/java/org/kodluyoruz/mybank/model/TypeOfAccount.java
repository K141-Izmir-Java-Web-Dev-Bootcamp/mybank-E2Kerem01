package org.kodluyoruz.mybank.model;


public enum TypeOfAccount {
    ACCUMULATION_ACCOUNT(1), CURRENT_ACCOUNT(2);
    private int type;
    TypeOfAccount(int type){
        this.type = type;
    }

    public int getType(){
        return this.type;
    }
}

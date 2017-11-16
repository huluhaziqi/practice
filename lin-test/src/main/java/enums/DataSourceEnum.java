package enums;

public enum  DataSourceEnum {

    MASTER("lin",true),
    SLAVE("lin2",false);

    DataSourceEnum(String name, boolean isMaster) {
        this.name = name;
        this.isMaster = isMaster;
    }

    private String name;

    private boolean isMaster;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public void setMaster(boolean master) {
        isMaster = master;
    }

    public String getDefault(){
        String defaultDatasource = "";
        for(DataSourceEnum dataSourceEnum : DataSourceEnum.values()){
            if(!defaultDatasource.equals("")){
                break;
            }
            if(dataSourceEnum.isMaster){
                defaultDatasource = dataSourceEnum.getName();
            }
        }
        return defaultDatasource;
    }
}

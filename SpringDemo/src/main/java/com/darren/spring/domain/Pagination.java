package com.darren.spring.domain;

public class Pagination {
	  /**
     * 默认每页分页条数
     */
    public static final int DEF_COUNT = 25;
    
    /**
     * 开始行
     */
    private int startRow;
    
    /**
     * 结束行
     */
    private int limitRow;
    
    /**
     * 总条数
     */
    private int totalCount;
    
    /**
     * <默认构造函数>
     */
    public Pagination()
    {
        //设定每页显示的数量为25
        limitRow = DEF_COUNT;
    }
    
    public int getStartRow()
    {
        return startRow;
    }
    
    /** <一句话功能简述>
     * <功能详细描述>
     * @param startRow startRow
     * @see [类、类#方法、类#成员]
     */
    public void setStartRow(int startRow)
    {
        if (startRow < 0)
        {
            startRow = 0;
        }
        else
        {
            this.startRow = startRow;
        }
    }
    
    public int getLimitRow()
    {
        return limitRow;
    }
    
    /** <一句话功能简述>
     * <功能详细描述>
     * @param limitRow limitRow
     * @see [类、类#方法、类#成员]
     */
    public void setLimitRow(int limitRow)
    {
        if (limitRow <= 0)
        {
            limitRow = DEF_COUNT;
        }
        else
        {
            this.limitRow = limitRow;
        }
    }
    
    public int getTotalCount()
    {
        return totalCount;
    }
    
    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }
}

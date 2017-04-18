package com.devils.hr.querys;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndyL on 2017/4/11.
 * 包含查询结果 条目数 总条目数
 */
public class ListQueryResult<T> {

    private List<T> list;

    private long    count;

    private long    totalCount;

    private boolean isEnd;

    private ListQueryResult() {}

    public static <R> ListQueryResult<R> create(List<R> list, long count, long totalCount, boolean isEnd){
        ListQueryResult<R> listQueryResult = new ListQueryResult<>();
        listQueryResult.setList(list == null ? new ArrayList<>() : list);
        listQueryResult.setCount(count);
        listQueryResult.setTotalCount(totalCount);
        listQueryResult.setEnd(isEnd);

        return listQueryResult;
    }

    public static <R> ListQueryResult<R> create(List<R> list, long count, long totalCount){
        ListQueryResult<R> listQueryResult = new ListQueryResult<>();
        listQueryResult.setList(list == null ? new ArrayList<>() : list);
        listQueryResult.setCount(count);
        listQueryResult.setTotalCount(totalCount);
        listQueryResult.setEnd(true);

        return listQueryResult;
    }

    public <W> List<W> convertToResp(Class<W> respModule){
        List<W> respList = new ArrayList<>();
        list.forEach(t -> {
            try {
                respList.add(respModule.getConstructor(t.getClass()).newInstance(t));
            } catch (InstantiationException | IllegalAccessException
                    | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });

        return respList;
    }

    public List<T> getList() {
        return list;
    }

    public T getLastElement(){
        return (list == null || list.size() < 1) ? null : list.get(list.size() - 1);
    }

    public long getCount() {
        return count;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public boolean isEnd() {
        return isEnd;
    }

    private void setList(List<T> list) {
        this.list = list;
    }

    private void setCount(long count) {
        this.count = count;
    }

    private void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    private void setEnd(boolean end) {
        isEnd = end;
    }

    @Override
    public String toString() {
        return "ListQueryResult{" +
                "list=" + list +
                ", count=" + count +
                ", totalCount=" + totalCount +
                ", isEnd=" + isEnd +
                '}';
    }
}

package shared;

import rpc.RPCMethod;
import shared.filter.descriptor.ExpressionDescription;

import java.util.List;

public interface StudentDao {
    @RPCMethod
    public Integer add(Student st);
    @RPCMethod
    public Void remove(Student st);
    @RPCMethod
    public Student get(int id);
    @RPCMethod
    public List<Student> filter(ExpressionDescription ed);
    @RPCMethod
    public List<Student> getAll();
}
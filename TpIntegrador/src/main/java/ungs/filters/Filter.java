package ungs.filters;

public interface Filter<INPUT, OUTPUT > {

    OUTPUT applyFilter(INPUT input);

}

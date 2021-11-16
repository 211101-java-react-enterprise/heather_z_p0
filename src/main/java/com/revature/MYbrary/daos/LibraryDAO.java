package com.revature.MYbrary.daos;

import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.util.List;

public class LibraryDAO implements CrudDAO<Library> {

    @Override
    public Library save(Library newObj) {
        return null;
    }

    @Override
    public List<Library> findAll() {
        return null;
    }

    @Override
    public Library findById(String id) {
        return null;
    }

    @Override
    public boolean update(Library updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }
}

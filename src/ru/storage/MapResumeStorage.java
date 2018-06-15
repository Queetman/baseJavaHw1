package ru.storage;

import ru.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected List<Resume> getSortableList() {
        return new ArrayList<>(map.values());
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected Resume getResume(Resume resume) {
        return resume;
    }

    @Override
    protected void updateResume(Resume res, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Resume resume) {
        map.remove((resume).getUuid());
    }

    @Override
    protected void saveNewResume(Resume resume, Resume res) {
        map.put(resume.getUuid(), resume);
    }
}

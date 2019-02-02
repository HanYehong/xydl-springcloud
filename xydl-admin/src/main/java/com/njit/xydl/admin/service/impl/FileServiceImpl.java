package com.njit.xydl.admin.service.impl;

import com.njit.xydl.admin.dao.FileInfoMapper;
import com.njit.xydl.admin.entity.FileInfo;
import com.njit.xydl.admin.entity.FileInfoExample;
import com.njit.xydl.admin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public long countByExample(FileInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(FileInfoExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(FileInfo record) {
        return fileInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(FileInfo record) {
        return 0;
    }

    @Override
    public List<FileInfo> selectByExample(FileInfoExample example) {
        return null;
    }

    @Override
    public FileInfo selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(FileInfo record, FileInfoExample example) {
        return 0;
    }

    @Override
    public int updateByExample(FileInfo record, FileInfoExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(FileInfo record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(FileInfo record) {
        return 0;
    }
}

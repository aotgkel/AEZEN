package com.aezen.www.vo;

/*
 * 클래스 기능 : 파일 정보 (file 테이블)
 */
public class FileVO {
    private int fileNo; // 파일 번호 (Primary Key)
    private String logicalFileName; // 사용자가 업로드한 원본 파일 이름
    private String physicalFileName; // 서버에 저장된 실제 파일 이름 (UUID 등)
    private long fileSize; // 파일 크기 (바이트)
    private String fileExt; // 파일 확장자
    private int boardNo; // 파일이 연결된 게시글 번호 (Foreign Key)

    // =============================
    // Getters
    // =============================

    public int getFileNo() {
        return fileNo;
    }

    public String getLogicalFileName() {
        return logicalFileName;
    }

    public String getPhysicalFileName() {
        return physicalFileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileExt() {
        return fileExt;
    }

    public int getBoardNo() {
        return boardNo;
    }

    // =============================
    // Setters
    // =============================

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    public void setLogicalFileName(String logicalFileName) {
        this.logicalFileName = logicalFileName;
    }

    public void setPhysicalFileName(String physicalFileName) {
        this.physicalFileName = physicalFileName;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }
}
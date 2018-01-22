package com.blogspot.androidhlp.afhbrowserv2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rocket on 15-01-2018.
 */

public class FileSummaryRVAdapter extends RecyclerView.Adapter<FileSummaryRVAdapter.FileSummaryRVViewHolder> {
    private Context mContext;
    private List<FileInfoSummary> FileInfoSummaryList;

    public FileSummaryRVAdapter(Context mContext, List<FileInfoSummary> fileInfoSummaryList) {
        this.mContext = mContext;
        this.FileInfoSummaryList = fileInfoSummaryList;
    }

    @Override
    public FileSummaryRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.fileinfo_summary_rv, null);
        return new FileSummaryRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FileSummaryRVViewHolder holder, int position) {
        FileInfoSummary currentFileSummary = FileInfoSummaryList.get(position);
        holder.fileNameTV.setText(currentFileSummary.getFileName());
        holder.fileSizeTV.setText(currentFileSummary.getFileSize());
        holder.fileDownloadsTV.setText(currentFileSummary.getDownloads());
        holder.fileUploadDateTV.setText(currentFileSummary.getUploadDate());
    }

    @Override
    public int getItemCount() {
        return FileInfoSummaryList.size();
    }

    class FileSummaryRVViewHolder extends RecyclerView.ViewHolder {
        TextView fileNameTV, fileSizeTV, fileDownloadsTV, fileUploadDateTV;

        public FileSummaryRVViewHolder(View itemView) {
            super(itemView);
            fileNameTV = (TextView)itemView.findViewById(R.id.fileNameRV);
            fileSizeTV = (TextView)itemView.findViewById(R.id.fileSizeRV);
            fileDownloadsTV = (TextView)itemView.findViewById(R.id.fileDownloadsRV);
            fileUploadDateTV = (TextView)itemView.findViewById(R.id.fileUploadDateRV);
        }
    }
}

package com.fms.processor.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fms.processor.domain.EventParticipantInfo;
import com.fms.processor.domain.EventParticipantRegistry;
import com.fms.processor.domain.EventSummary;
import com.fms.processor.repository.EventParticipantInfoRepository;
import com.fms.processor.repository.EventParticipantRegistryRepository;
import com.fms.processor.repository.EventSummaryRepository;
import com.fms.processor.util.ExcelUtil;

@Service
public class ExcelEventProcessor implements EventProcessor {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private EventParticipantInfoRepository eventParticipantInfoRepo;

	@Autowired
	private EventParticipantRegistryRepository registryRepository;

	@Autowired
	private EventSummaryRepository summaryRepository;

	@Value("${event.processed-files.filepath}")
	private String processedFilePath;

	@Override
	public void processEventParticipantInfo(String filePath) {
		Workbook workbook = null;
		File file = new File(filePath);
		try {
			log.info("processing files for event ParticipantInfo");
			workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			List<EventParticipantInfo> participantInfos = new ArrayList<>();
			sheet.forEach(row -> {
				if (row.getRowNum() > 0) {
					participantInfos.add(ExcelUtil.buildEventParticipantInfo(row));
				}
			});
			if (CollectionUtils.isNotEmpty(participantInfos))
				eventParticipantInfoRepo.saveAll(participantInfos);
		} catch (Exception e) {
			log.error("Exception occurred on processing EventParticipantInfo {},", e);
		} finally {
			closeWorkbook(workbook);
			moveToProcessed(file);
		}
	}

	@Override
	public void processEventSummary(String filePath) {
		Workbook workbook = null;
		File file = new File(filePath);
		try {
			log.info("processing files for event summary");
			workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			List<EventSummary> summaries = new ArrayList<>();
			sheet.forEach(row -> {
				if (row.getRowNum() > 0) {
					summaries.add(ExcelUtil.buildEventSummary(row));
				}
			});
			if (CollectionUtils.isNotEmpty(summaries))
				summaryRepository.saveAll(summaries);
		} catch (Exception e) {
			log.error("Exception occurred on processing EventSummary {},", e);
		} finally {
			closeWorkbook(workbook);
			moveToProcessed(file);
		}

	}

	@Override
	public void processParticipantNotAttended(String filePath) {
		Workbook workbook = null;
		File file = new File(filePath);
		try {
			log.info("processing files for event Participant not-attended");
			workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			List<EventParticipantRegistry> participantRegistries = new ArrayList<>();
			sheet.forEach(row -> {
				if (row.getRowNum() > 0) {
					participantRegistries.add(ExcelUtil.buildEventRegistry(row, true, false));
				}
			});
			if (CollectionUtils.isNotEmpty(participantRegistries))
				registryRepository.saveAll(participantRegistries);
		} catch (Exception e) {
			log.error("Exception occurred on processing ParticipantNotAttended {},", e);
		} finally {
			closeWorkbook(workbook);
			moveToProcessed(file);
		}

	}

	@Override
	public void processParticipantUnregistered(String filePath) {
		Workbook workbook = null;
		File file = new File(filePath);
		try {
			log.info("processing files for event Participant unregistered");
			workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			List<EventParticipantRegistry> participantRegistries = new ArrayList<>();
			sheet.forEach(row -> {
				if (row.getRowNum() > 0) {
					participantRegistries.add(ExcelUtil.buildEventRegistry(row, false, false));
				}
			});
			if (CollectionUtils.isNotEmpty(participantRegistries))
				registryRepository.saveAll(participantRegistries);
		} catch (Exception e) {
			log.error("Exception occurred on processing ParticipantUnregistered {}", e);
		} finally {
			closeWorkbook(workbook);
			moveToProcessed(file);
		}

	}

	private void moveToProcessed(File file) {
		try {
			String fileName = file.getName();
			long timestamp = System.currentTimeMillis();
			String moveToPath = processedFilePath + FilenameUtils.removeExtension(fileName) + "_" + timestamp + "."
					+ FilenameUtils.getExtension(fileName);
			Path temp = Files.move(file.toPath(), Paths.get(moveToPath));
			Optional.of(temp).ifPresent(t -> {
				log.info("file {} moved successfully", moveToPath);
			});
		} catch (Exception e) {
			log.error("Exception occurred when moving file {} to processed", file.getName(), e);
		}
	}
	
	private void closeWorkbook(Workbook workbook) {
		try {
			if (workbook != null) {
				workbook.close();
			}
		} catch (IOException e) {
			log.error("Closing workbook failed {}", e);
		}
	}

}

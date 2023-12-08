package gr.ihu.dw.service;

import gr.ihu.dw.dao.Vc;
import gr.ihu.dw.dao.VcRepository;
import gr.ihu.dw.dto.VcsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VcService {

    private final VcRepository vcRepository;
    private static final Logger logger = LoggerFactory.getLogger(VcService.class);

    public VcService(VcRepository vcRepository) {
        this.vcRepository = vcRepository;
    }

    public List<VcsDTO> fetchVcs() {
        List<Vc> vcs = vcRepository.findAll();
        List<VcsDTO> vcsDTOS = vcs.stream()
                .map(VcsDTO::fromVc)
                .collect(Collectors.toList());

        return vcsDTOS;
    }

    public void deleteById(String id) {
        vcRepository.deleteById(id);
    }

}

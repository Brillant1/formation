package bj.formation.demoprojet.mappers;


import bj.formation.demoprojet.dtos.AgentDto;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.requests.AgentRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AgentMapper {
    public Agent toAgent(AgentRequest request){
        Agent agent = new Agent();
        BeanUtils.copyProperties(request, agent);
        return agent;
    }

    public AgentRequest toRequest(Agent agent){
        AgentRequest agentRequest = new AgentRequest();
        BeanUtils.copyProperties(agent, agentRequest);
        return  agentRequest;
    }

    public Agent fromDtoToAgent(AgentDto agentDto){
        Agent agent = new Agent();
        BeanUtils.copyProperties(agentDto, agent);
        return agent;
    }

    public AgentDto toDto(Agent agent){
        AgentDto agentDto = new AgentDto();
        BeanUtils.copyProperties(agent, agent);
        return  agentDto;
    }
}

package com.NeoCamp.soccer_matches.dto.util;


public class PromptUtil {


    public static final String PROMPT = """
            Você é um especialista em futebol e estatísticas de partidas. Sua tarefa é analisar as informações fornecidas sobre dois times de futebol e calcular a probabilidade de vitória de cada um deles.
            
            Informações dos times:
            - Time A: %s
            - Time B: %s
            
            Baseando-se no historico de vitorias em casa, vitoria fora de casa, forneça a probabilidade de vitória para cada time.
            
            - Time A: Vitoria em casa: %s, Vitoria fora de casa: %s
            - Time B: Vitoria em casa: %s, Vitoria fora de casa: %s
            
            Responda no seguinte formato JSON:
            {
                "response": [
                    {
                        "teamName": "Nome do time A",
                        "chanceVictory": Probabilidade de vitória do time A (0 a 100)
                    },
                    {
                        "teamName": "Nome do time B",
                        "chanceVictory": Probabilidade de vitória do time B (0 a 100)
                    }
                ]
            }
            """;

    public static String getPrompt(String teamA, String teamB, String homeWinA, String awayWinA, String homeWinB, String awayWinB) {
        return String.format(PROMPT, teamA, teamB, homeWinA, awayWinA, homeWinB, awayWinB);
    }
}

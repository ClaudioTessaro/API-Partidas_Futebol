package com.neocamp.soccer_matches.dto.util;


public class PromptUtil {


    public static final String PROMPT = """
            Você é um especialista em futebol e estatísticas de partidas. Sua tarefa é analisar as informações fornecidas sobre dois times de futebol e calcular a probabilidade de vitória de cada um deles.
            Considerando que o time A é o time da casa e o time B é o time visitante, você deve fornecer uma análise detalhada com base nas seguintes informações:
            Informações dos times:
            - Time A: %s
            - Time B: %s
            
            Baseando-se no historico de vitorias em casa, vitoria fora de casa, derrotas fora de casa, derrotas em casa, empate fora de casa e empate em casa, forneça a probabilidade de vitória para cada time.
            
            - Time A: Vitoria em casa: %s, Vitoria fora de casa: %s, Derrota em casa: %s, Derrota fora de casa: %s, Empate em casa: %s, Empate fora de casa: %s
            - Time B: Vitoria em casa: %s, Vitoria fora de casa: %s, Derrota em casa: %s, Derrota fora de casa: %s, Empate em casa: %s, Empate fora de casa: %s
            
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

    public static String getPrompt(String teamA, String teamB, String homeWinA, String awayWinA, String homeWinB, String awayWinB, String drawHomeA, String drawAwayA, String drawHomeB, String drawAwayB, String lossHomeA, String lossAwayA, String lossHomeB, String lossAwayB) {
        return String.format(PROMPT, teamA, teamB,
                homeWinA, awayWinA, lossHomeA, lossAwayA, drawHomeA, drawAwayA,
                homeWinB, awayWinB, lossHomeB, lossAwayB, drawHomeB, drawAwayB);
    }
}

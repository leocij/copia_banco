package copia_banco;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        String sobrouMesPassadoSelect = "select * from sobrou_mes_passado";
        ResultSet resultSet1 = getResultSet(sobrouMesPassadoSelect);
        while (resultSet1.next()) {
            String dataStr = resultSet1.getString("data");
            String sobrouMesPassadoStr = resultSet1.getString("sobrou_mes_passado");

            System.out.println("SobrouMesPassado: " + dataStr + sobrouMesPassadoStr);

            String sobrouMesPassadoInsert = "insert into sobrou_mes_passado (data,sobrou_mes_passado) values ('"+dataStr+"','"+sobrouMesPassadoStr+"')";
            insert(sobrouMesPassadoInsert);
        }

        String dataControleSelect = "select * from data_controle";
        ResultSet resultSet2 = getResultSet(dataControleSelect);
        while (resultSet2.next()) {
            String dataControleStr = resultSet2.getString("data_controle");

            System.out.println("DataControle: " +dataControleStr);

            String dataControleInsert = "insert into data_controle (data_controle) values ('"+dataControleStr+"')";
            insert(dataControleInsert);
        }

        String clienteSelect = "select * from cliente";
        ResultSet resultSet3 = getResultSet(clienteSelect);
        while(resultSet3.next()) {
            String nomeStr = resultSet3.getString("nome");

            System.out.println("Cliente: " + nomeStr);

            String clienteInsert = "insert into cliente (nome) values ('"+nomeStr+"')";
            insert(clienteInsert);
        }

        String ganhoSelect = "select * from ganho";
        ResultSet resultSet4 = getResultSet(ganhoSelect);
        while(resultSet4.next()) {
            String dataStr = resultSet4.getString("data");
            String diaSemanaStr = resultSet4.getString("dia_semana");
            String clienteStr = resultSet4.getString("cliente");
            String statusStr = resultSet4.getString("status");
            String sessaoStr = resultSet4.getString("sessao");
            String quantidadeStr = resultSet4.getString("quantidade");
            String valorStr = resultSet4.getString("valor");

            System.out.println("Ganho: " +dataStr+diaSemanaStr+clienteStr+statusStr+sessaoStr+quantidadeStr+valorStr);

            String ganhoInsert = "insert into ganho (data,dia_semana,cliente,status,sessao,quantidade,valor) values ('"+dataStr+"','"+diaSemanaStr+"','"+clienteStr+"','"+statusStr+"','"+sessaoStr+"','"+quantidadeStr+"','"+valorStr+"')";
            insert(ganhoInsert);
        }

        String fixaSelect = "select * from fixa";
        ResultSet resultSet5 = getResultSet(fixaSelect);
        while(resultSet5.next()){
            String descricaoStr = resultSet5.getString("descricao");
            String vencimentoStr = resultSet5.getString("vencimento");
            String valorStr = resultSet5.getString("valor");

            System.out.println("Fixa: " +descricaoStr+vencimentoStr+valorStr);

            String fixaInsert = "insert into fixa (descricao,vencimento,valor) values ('"+descricaoStr+"','"+vencimentoStr+"','"+valorStr+"')";
            insert(fixaInsert);
        }

        String parcelamentoSelect = "select * from parcelamento";
        ResultSet resultSet6 = getResultSet(parcelamentoSelect);
        while (resultSet6.next()){
            String descricaoStr = resultSet6.getString("descricao");
            String vencimentoStr = resultSet6.getString("vencimento");
            String valorParcelaStr = resultSet6.getString("valor_parcela");
            String valorTotalStr = resultSet6.getString("valor_total");
            String numeroParcelaStr = resultSet6.getString("numero_parcela");
            String totalParcelaStr = resultSet6.getString("total_parcela");
            String statusStr = resultSet6.getString("status");
            String dataAlteracaoStr = resultSet6.getString("data_alteracao");

            System.out.println("Parcelamento: " +descricaoStr+vencimentoStr+valorParcelaStr+valorTotalStr+numeroParcelaStr+totalParcelaStr+statusStr+dataAlteracaoStr);

            String parcelamentoInsert = "insert into parcelamento (descricao,vencimento,valor_parcela,valor_total,numero_parcela,total_parcela,status,data_alteracao) values ('"+descricaoStr+"','"+vencimentoStr+"','"+valorParcelaStr+"','"+valorTotalStr+"','"+numeroParcelaStr+"','"+totalParcelaStr+"','"+statusStr+"','"+dataAlteracaoStr+"')";
            insert(parcelamentoInsert);
        }

        String entradaSelect = "select * from entrada";
        ResultSet resultSet7 = getResultSet(entradaSelect);
        while (resultSet7.next()) {
            String dataHoraStr = resultSet7.getString("data_hora");
            String descricaoStr = resultSet7.getString("descricao");
            String valorStr = resultSet7.getString("valor");
            String ultimaEdicaoStr = resultSet7.getString("ultima_edicao");

            System.out.println("Entrada: "+dataHoraStr+descricaoStr+valorStr+ultimaEdicaoStr);

            String entradaInsert = "insert into entrada (data_hora,descricao,valor,ultima_edicao) values ('"+dataHoraStr+"','"+descricaoStr+"','"+valorStr+"','"+ultimaEdicaoStr+"')";
            insert(entradaInsert);
        }

        String saidaSelect = "select * from saida";
        ResultSet resultSet8 = getResultSet(saidaSelect);
        while (resultSet8.next()) {
            String dataHoraStr = resultSet8.getString("data_hora");
            String descricaoStr = resultSet8.getString("descricao");
            String valorStr = resultSet8.getString("valor");
            String ultimaEdicaoStr = resultSet8.getString("ultima_edicao");

            System.out.println("Saida: " +dataHoraStr+descricaoStr+valorStr+ultimaEdicaoStr);

            String saidaInsert = "insert into saida (data_hora,descricao,valor,ultima_edicao) values ('"+dataHoraStr+"','"+descricaoStr+"','"+valorStr+"','"+ultimaEdicaoStr+"')";
            insert(saidaInsert);
        }

        System.out.println("Aplicacao finalizada com sucesso!");
    }

    private static void insert(String sqlInsert) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:database_v1/db","SA","");
            statement = connection.createStatement();
            statement.executeQuery(sqlInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert statement != null;
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static ResultSet getResultSet(String sqlSelect) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:database/db","SA","");
            statement = connection.createStatement();
            return statement.executeQuery(sqlSelect);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert statement != null;
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}

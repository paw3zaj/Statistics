import React from "react";

const History = () => {

    return(
        <h1>History</h1>
        // <p>history</p>
        // <table>
        //     <thead>
        //     <tr>
        //         <th th:text="#{virtuaLogs.id}">index</th>
        //         <th th:text="#{virtuaLogs.date}">data zmiany</th>
        //         <th th:text="#{virtuaLogs.virtuaId}">virtua id</th>
        //         <th th:text="#{virtuaLogs.signature}">sygnatura</th>
        //         <th th:text="#{virtuaLogs.barcode}">kod kreskowy</th>
        //         <th th:text="#{virtuaLogs.author}">autor</th>
        //         <th th:text="#{virtuaLogs.title}">tytuł</th>
        //         <th th:text="#{virtuaLogs.status}">status</th>
        //     </tr>
        //     </thead>
        //     <tbody>
        //     <tr th:each="vl : ${allVirtuaLogs}">
        //         <td th:text="${vl.getId()}"/>
        //         <td th:text="${vl.getCreatedDate()}"/>
        //         <td th:text="${vl.getIdVirtua()}"/>
        //         <td th:text="${vl.getSignature()}"/>
        //         <td th:text="${vl.getBarcode()}"/>
        //         <td th:text="${vl.getAuthor()}"/>
        //         <td th:text="${vl.getTitle()}"/>
        //         <td th:text="${vl.getStatus()}"/>
        //     </tr>
        //     </tbody>
        // </table>
    );
}

export default History
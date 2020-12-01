import React from "react"
import Table from "./Table"
  
const History = ({data}) => {

    const columns = React.useMemo(
        () => [
          {
            Header: 'Data zmiany',
            accessor: 'createdDate'
          },
          {
            Header: 'Virtua id',
            accessor: 'idVirtua'
          },
          {
            Header: 'Sygnatura',
            accessor: 'signature'
          },
          {
            Header: 'Kod kreskowy',
            accessor: 'barcode'
          },
          {
            Header: 'Autor',
            accessor: 'author'
          },
          {
            Header: 'Tytół',
            accessor: 'title'
          },
          {
            Header: 'Status',
            accessor: 'status'
          },
        ],
        []
      )

    return(
        <Table columns={columns} data={data} />
    );
}

export default History
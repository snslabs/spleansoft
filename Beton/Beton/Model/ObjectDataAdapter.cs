using System;
using System.Collections.Generic;
using System.Data;

namespace Beton.Model
{
    class ObjectDataAdapter : IDataAdapter
    {
        private List<Matherial> data;

        public ObjectDataAdapter(List<Matherial> data)
        {
            this.data = data;
        }


        public DataTable[] FillSchema(DataSet dataSet, SchemaType schemaType)
        {
            throw new NotImplementedException();
        }

        public int Fill(DataSet dataSet)
        {
            throw new NotImplementedException();
        }

        public IDataParameter[] GetFillParameters()
        {
            throw new NotImplementedException();
        }

        public int Update(DataSet dataSet)
        {
            throw new NotImplementedException();
        }

        public MissingMappingAction MissingMappingAction
        {
            get { throw new NotImplementedException(); }
            set { throw new NotImplementedException(); }
        }

        public MissingSchemaAction MissingSchemaAction
        {
            get { throw new NotImplementedException(); }
            set { throw new NotImplementedException(); }
        }

        public ITableMappingCollection TableMappings
        {
            get { throw new NotImplementedException(); }
        }
    }
}

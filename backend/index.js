const { ApolloServer, gql } = require('apollo-server');

// A schema is a collection of type definitions (hence "typeDefs")
// that together define the "shape" of queries that are executed against
// your data.
const typeDefs = gql`
  # This "User" type defines the queryable fields for every user in our data source.
  type User {
    username: String
    email: String
  }

  type Workout {
    name: String
  }

  # The "Query" type is special: it lists all of the available queries that
  # clients can execute, along with the return type for each. In this
  # case, the "users" query returns an array of zero or more Users (defined above).
  type Query {
    users: [User]
    workouts: [Workout]
  }
`;

  const users = []

  const workouts = [
  {
    name: 'Situps',
  },
  {
    name: 'Pushups',
  }
  ]

  // Resolvers define the technique for fetching the types defined in the
  // schema. This resolver retrieves books from the "books" array above.
  const resolvers = {
    Query: {
      users: () => users,
      workouts: () => workouts,
    },
  };

  // The ApolloServer constructor requires two parameters: your schema
  // definition and your set of resolvers.
  const server = new ApolloServer({ typeDefs, resolvers });

  // The `listen` method launches a web server.
  server.listen().then(({ url }) => {
    console.log(`🚀  Server ready at ${url}`);
  });

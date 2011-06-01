namespace Beton.Behavior
{
    interface IBetonComponent
    {
        bool SaveData();
        void LoadData();
        string FormCaption { get; }
    }
}
